package eu.righettod.pocauthztesting.vo;

/**
 * Represent user authentication information
 */
public class User {
    private String login;
    private String password;
    private String accessToken;
    private boolean authenticationState;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isAuthenticationState() {
        return authenticationState;
    }

    public void setAuthenticationState(boolean authenticationState) {
        this.authenticationState = authenticationState;
    }
}
