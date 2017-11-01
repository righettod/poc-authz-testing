package eu.righettod.pocauthztesting.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import eu.righettod.pocauthztesting.enumeration.SecurityRole;
import eu.righettod.pocauthztesting.vo.Message;
import eu.righettod.pocauthztesting.vo.MessageActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provide different business services that are protected by the authorization matrix through the access token.
 */
@RestController
@EnableAutoConfiguration
public class BusinessService {

    private static final Logger TRACE = LoggerFactory.getLogger(BusinessService.class);
    private static final List<Message> MSG_STORAGE = new ArrayList<>();
    private transient byte[] jwtSecret;

    /**
     * Constructor
     *
     * @throws IOException If JWT token secret cannot be read
     */
    public BusinessService() throws IOException {
        this.jwtSecret = Files.readAllBytes(Paths.get("jwt-secret.txt"));
    }

    /**
     * Create a new message.
     * <p>
     * <code>curl -X PUT --data "{\"content\":\"test\"}" -H "Content-Type: application/json" -H "Authorization: eyJ0e..." http://localhost:8080/</code>
     *
     * @param msg         Information of the message to create
     * @param accessToken Access token taken from the Authorization HTTP header
     * @param response    Http response
     * @return JSON response with the information about the processing status of the request
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public MessageActionResult create(@RequestHeader("Authorization") String accessToken, @RequestBody Message msg, HttpServletResponse response) {
        MessageActionResult resultStatusInfo = new MessageActionResult();
        resultStatusInfo.setActionPerformed("CREATE_NEW_MESSAGE");

        try {
            //Check authorization (only authenticated user can create a message) and if OK do the job...
            DecodedJWT token = this.validateAccessToken(accessToken);
            if (!SecurityRole.ADMIN.name().equalsIgnoreCase(token.getClaim("role").asString()) && !SecurityRole.BASIC.name().equalsIgnoreCase(token.getClaim("role").asString())) {
                resultStatusInfo.setMessageIdentifier("");
                resultStatusInfo.setSucceed(false);
                resultStatusInfo.setReason("Access denied");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                //Create a new message
                Message newMsg = new Message();
                newMsg.setContent(msg.getContent());
                newMsg.setIdentifier(UUID.randomUUID().toString());
                newMsg.setOwner(token.getSubject());
                MSG_STORAGE.add(newMsg);
                //Set creation info
                resultStatusInfo.setMessageIdentifier(newMsg.getIdentifier());
                resultStatusInfo.setSucceed(true);
                resultStatusInfo.setReason("Creation OK");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            resultStatusInfo.setMessageIdentifier("");
            resultStatusInfo.setSucceed(false);
            resultStatusInfo.setReason("");
            TRACE.error("Error during the creation of a new message !", e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        return resultStatusInfo;

    }

    /**
     * Delete a message.
     * <p>
     * <code>curl -X DELETE -H "Authorization: eyJ0e..." http://localhost:8080/25a311b1-0d85-40a0-9cc3-6680c57b5348</code>
     *
     * @param accessToken Access token taken from the Authorization HTTP header
     * @param messageId   Message to process
     * @param response    Http response
     * @return JSON response with the information about the processing status of the request
     */
    @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MessageActionResult delete(@RequestHeader("Authorization") String accessToken, @PathVariable String messageId, HttpServletResponse response) {
        MessageActionResult resultStatusInfo = new MessageActionResult();
        resultStatusInfo.setActionPerformed("DELETE_MESSAGE");

        try {
            //Check authorization (only admin user can delete a message) and if OK do the job...
            DecodedJWT token = this.validateAccessToken(accessToken);
            if (!SecurityRole.ADMIN.name().equalsIgnoreCase(token.getClaim("role").asString())) {
                resultStatusInfo.setMessageIdentifier("");
                resultStatusInfo.setSucceed(false);
                resultStatusInfo.setReason("Access denied");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                //Delete the message from the list
                Message m = new Message();
                m.setIdentifier(messageId);
                MSG_STORAGE.remove(m);
                //Set creation info
                resultStatusInfo.setMessageIdentifier(messageId);
                resultStatusInfo.setSucceed(true);
                resultStatusInfo.setReason("Delete OK");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            resultStatusInfo.setMessageIdentifier("");
            resultStatusInfo.setSucceed(false);
            resultStatusInfo.setReason("");
            TRACE.error("Error during the deletion of the message {}", messageId, e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        return resultStatusInfo;
    }

    /**
     * Get a specific message or all messages.
     * <p>
     * <code>curl -X GET http://localhost:8080/25a311b1-0d85-40a0-9cc3-6680c57b5348</code>
     * <code>curl -X GET http://localhost:8080/</code>
     *
     * @param messageId Message to process
     * @param response  Http response
     * @return JSON response with the list of messages
     */
    @RequestMapping(value = {"/", "/{messageId}"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Message> list(@PathVariable Optional<String> messageId, HttpServletResponse response) {
        List<Message> result = new ArrayList<>();

        try {
            //Everyone can see the message (not authentication and authorization required)
            if (!messageId.isPresent()) {
                result.addAll(MSG_STORAGE);
            } else {
                Optional<Message> m = MSG_STORAGE.stream().filter(i -> i.getIdentifier().equals(messageId.get())).findFirst();
                if (m.isPresent()) {
                    result.add(m.get());
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            result.clear();
            TRACE.error("Error during the listing of the message", e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        return result;
    }


    /**
     * Validate a received JWT token.
     *
     * @param accessToken Access token to validate
     * @return The decoded token if the token is valid
     * @throws JWTVerificationException If the token validation failed
     */
    private DecodedJWT validateAccessToken(String accessToken) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("srv").build();
        return verifier.verify(accessToken);
    }

}
