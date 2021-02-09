 package apiEngine.model.requests;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationRequest {

    private String userName;
    private String password;
   // private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public AuthorizationRequest() {
    }

    /**
     *
     * @param password
     * @param userName
     */
    public AuthorizationRequest(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}