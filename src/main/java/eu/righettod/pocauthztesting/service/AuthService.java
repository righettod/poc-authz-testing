package eu.righettod.pocauthztesting.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import eu.righettod.pocauthztesting.enumeration.SecurityRole;
import eu.righettod.pocauthztesting.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 * Provide authentication service (handle JWT token)
 */
@RestController
@EnableAutoConfiguration
public class AuthService {

    private static final Logger TRACE = LoggerFactory.getLogger(AuthService.class);
    private transient byte[] jwtSecret;

    /**
     * Constructor
     *
     * @throws IOException If JWT token secret cannot be read
     */
    public AuthService() throws IOException {
        this.jwtSecret = Files.readAllBytes(Paths.get("jwt-secret.txt"));
    }

    /**
     * Perform authentication and return user information's authentication state as serialized JSON data.
     * <p>
     * <code>curl -v -X POST --data "{\"login\":\"admin\",\"password\":\"1234\"}" -H "Content-Type: application/json" http://localhost:8080/auth</code>
     *
     * @param user     User login information serialized by SpringMVC
     * @param response Http response
     * @return JSON response with the authentication state
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User authenticate(@RequestBody User user, HttpServletResponse response) {
        try {
            //Authenticate user: Here for the example, the password "1234" is the only correct password
            if ("1234".equalsIgnoreCase(user.getPassword())) {
                //Get user role based on user login: ADMIN or BASIC (it's an example here)
                String role = ("admin".equalsIgnoreCase(user.getLogin()) ? "ADMIN" : "BASIC");
                //Build the access token
                String accessToken = issueAccessToken(user.getLogin(), SecurityRole.valueOf(role));
                user.setAccessToken(accessToken);
                user.setAuthenticationState(true);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                user.setAuthenticationState(false);
                user.setAccessToken("");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (Exception e) {
            TRACE.error("Error during authentication of user {} !", user.getLogin(), e);
            user.setAuthenticationState(false);
            user.setAccessToken("");
            response.setStatus(HttpServletResponse.SC_OK);
        } finally {
            user.setPassword("");
        }

        return user;
    }

    /**
     * Generate a JWT token the user and role specified.
     * Set public access here in order to be usable in tests
     *
     * @param login User login
     * @param role  Authorization logical role
     * @return The JWT token
     * @throws Exception If any error occurs during the creation
     */
    public String issueAccessToken(String login, SecurityRole role) throws Exception {
        if (login == null || role == null) {
            throw new IllegalArgumentException("All parameters must be not null");
        }
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 30);
        return JWT.create().withIssuer("srv").withSubject(login).withExpiresAt(c.getTime()).withClaim("role", role.name()).sign(algorithm);
    }
}
