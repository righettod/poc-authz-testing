package eu.righettod.pocauthztesting;

import eu.righettod.pocauthztesting.enumeration.SecurityRole;
import eu.righettod.pocauthztesting.service.AuthService;
import eu.righettod.pocauthztesting.vo.AuthorizationMatrix;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Integration Test cases in charge of validate the correct implementation of the authorization matrix.
 * Create on test case by logical role that will test access on all services exposed by the system.
 * Implements here focus on readability
 */
public class AuthorizationMatrixIT {

    /**
     * Object representation of the authorization matrix
     */
    private static AuthorizationMatrix AUTHZ_MATRIX;

    private static final String BASE_URL = "http://localhost:8080";


    /**
     * Load the authorization matrix in objects tree
     *
     * @throws Exception If any error occurs
     */
    @BeforeClass
    public static void globalInit() throws Exception {
        try (FileInputStream fis = new FileInputStream(new File("authorization-matrix.xml"))) {
            //See https://www.owasp.org/index.php/XML_External_Entity_(XXE)_Prevention_Cheat_Sheet#Unmarshaller
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            Source xmlSource = new SAXSource(spf.newSAXParser().getXMLReader(), new InputSource(fis));
            JAXBContext jc = JAXBContext.newInstance(AuthorizationMatrix.class);
            AUTHZ_MATRIX = (AuthorizationMatrix) jc.createUnmarshaller().unmarshal(xmlSource);
        }
    }

    /**
     * Test access to the services from a anonymous user.
     *
     * @throws Exception
     */
    @Test
    public void testAccessUsingAnonymousUserPointOfView() throws Exception {
        //Run the tests - No access token here
        List<String> errors = executeTestWithPointOfView(SecurityRole.ANONYMOUS, null);
        //Verify the test results
        Assert.assertEquals("Access issues detected using the ANONYMOUS USER point of view:\n" + formatErrorsList(errors), 0, errors.size());
    }

    /**
     * Test access to the services from a basic user.
     *
     * @throws Exception
     */
    @Test
    public void testAccessUsingBasicUserPointOfView() throws Exception {
        //Get access token representing the authorization for the associated point of view
        String accessToken = generateTestCaseAccessToken("basic", SecurityRole.BASIC);
        //Run the tests
        List<String> errors = executeTestWithPointOfView(SecurityRole.BASIC, accessToken);
        //Verify the test results
        Assert.assertEquals("Access issues detected using the BASIC USER point of view:\n " + formatErrorsList(errors), 0, errors.size());
    }

    /**
     * Test access to the services from a administrator user.
     *
     * @throws Exception
     */
    @Test
    public void testAccessUsingAdministratorUserPointOfView() throws Exception {
        //Get access token representing the authorization for the associated point of view
        String accessToken = generateTestCaseAccessToken("admin", SecurityRole.ADMIN);
        //Run the tests
        List<String> errors = executeTestWithPointOfView(SecurityRole.ADMIN, accessToken);
        //Verify the test results
        Assert.assertEquals("Access issues detected using the ADMIN USER point of view:\n" + formatErrorsList(errors), 0, errors.size());
    }

    /**
     * Evaluate the access to all service using the point of view (POV) specified.
     *
     * @param pointOfView Point of view to use
     * @param accessToken Access token that is linked to the point of view in terms of authorization.
     * @return List of errors detected
     * @throws Exception If any error occurs
     */
    private List<String> executeTestWithPointOfView(SecurityRole pointOfView, String accessToken) throws Exception {
        List<String> errors = new ArrayList<>();
        String errorMessageTplForUnexpectedReturnCode = "The service '%s' when called with POV '%s' return a response code %s that is not the expected one in allowed or denied case.";
        String errorMessageTplForIncorrectReturnCode = "The service '%s' when called with POV '%s' return a response code %s that is not the expected one (%s expected).";
        String fatalErrorMessageTpl = "The service '%s' when called with POV %s meet the error: %s";

        //Get the list of services to call
        List<AuthorizationMatrix.Services.Service> services = AUTHZ_MATRIX.getServices().getService();

        //Get the list of services test payload to use
        List<AuthorizationMatrix.ServicesTesting.Service> servicesTestPayload = AUTHZ_MATRIX.getServicesTesting().getService();

        //Call all services sequentially (no special focus on performance here)
        services.forEach(service -> {
            //Get the service test payload for the current service
            String payload = null;
            String payloadContentType = null;
            Optional<AuthorizationMatrix.ServicesTesting.Service> serviceTesting = servicesTestPayload.stream().filter(srvPld -> srvPld.getName().equals(service.getName())).findFirst();
            if (serviceTesting.isPresent()) {
                payload = serviceTesting.get().getPayload().getValue();
                payloadContentType = serviceTesting.get().getPayload().getContentType();
            }
            //Call the service and verify if the response is consistent
            try {
                //Call the service
                int serviceResponseCode = callService(service.getUri(), payload, payloadContentType, service.getHttpMethod(), accessToken);
                //Check if the role represented by the specified point of view is defined for the current service
                Optional<AuthorizationMatrix.Services.Service.Role> role = service.getRole().stream().filter(r -> r.getName().equals(pointOfView.name())).findFirst();
                boolean accessIsGrantedInAuthorizationMatrix = role.isPresent();
                //Verify behavior consistency according to the response code returned and the authorization configured in the matrix
                if (serviceResponseCode == service.getHttpResponseCodeForAccessAllowed()) {
                    //Roles is not in the list of role allowed to access to the service so it's an error
                    if (!accessIsGrantedInAuthorizationMatrix) {
                        errors.add(String.format(errorMessageTplForIncorrectReturnCode, service.getName(), pointOfView.name(), serviceResponseCode, service.getHttpResponseCodeForAccessDenied()));
                    }
                } else if (serviceResponseCode == service.getHttpResponseCodeForAccessDenied()) {
                    //Roles is in the list of role allowed to access to the service so it's an error
                    if (accessIsGrantedInAuthorizationMatrix) {
                        errors.add(String.format(errorMessageTplForIncorrectReturnCode, service.getName(), pointOfView.name(), serviceResponseCode, service.getHttpResponseCodeForAccessAllowed()));
                    }
                } else {
                    errors.add(String.format(errorMessageTplForUnexpectedReturnCode, service.getName(), pointOfView.name(), serviceResponseCode));
                }
            } catch (Exception e) {
                errors.add(String.format(fatalErrorMessageTpl, service.getName(), pointOfView.name(), e.getMessage()));
            }


        });

        return errors;
    }

    /**
     * Call a service with a specific payload and return the HTTP response code received.
     * Delegate this step in order to made the test cases more easy to maintain.
     *
     * @param uri                URI of the target service
     * @param payloadContentType Content type of the payload to send
     * @param payload            Payload to send
     * @param httpMethod         HTTP method to use
     * @param accessToken        Access token to specify to represent the identity of the caller
     * @return The HTTP response code received
     * @throws Exception If any error occurs
     */
    private int callService(String uri, String payload, String payloadContentType, String httpMethod, String accessToken) throws Exception {
        int rc;

        //Build the request - Use Apache HTTP Client in order to be more flexible in the combination
        HttpRequestBase request;
        String url = (BASE_URL + uri).replaceAll("\\{messageId\\}", "1");
        switch (httpMethod) {
            case "GET":
                request = new HttpGet(url);
                break;
            case "DELETE":
                request = new HttpDelete(url);
                break;
            case "PUT":
                request = new HttpPut(url);
                if (payload != null) {
                    request.setHeader("Content-Type", payloadContentType);
                    ((HttpPut) request).setEntity(new StringEntity(payload.trim()));
                }
                break;
            default:
                throw new UnsupportedOperationException(httpMethod + " not supported !");
        }
        request.setHeader("Authorization", (accessToken != null) ? accessToken : "");


        //Send the request and get the HTTP response code
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
                //Don't care here about the response content...
                rc = httpResponse.getStatusLine().getStatusCode();
            }
        }

        return rc;
    }

    /**
     * Generate a JWT token the user and role specified.
     *
     * @param login User login
     * @param role  Authorization logical role
     * @return The JWT token
     * @throws Exception If any error occurs during the creation
     */
    private String generateTestCaseAccessToken(String login, SecurityRole role) throws Exception {
        return new AuthService().issueAccessToken(login, role);
    }


    /**
     * Format a list of errors to a printable string
     *
     * @param errors Error list
     * @return Printable string
     */
    private String formatErrorsList(List<String> errors) {
        StringBuilder buffer = new StringBuilder();
        errors.forEach(e -> buffer.append(e).append("\n"));
        return buffer.toString();
    }
}
