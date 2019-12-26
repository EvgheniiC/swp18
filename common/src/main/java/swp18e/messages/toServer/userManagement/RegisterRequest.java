package swp18e.messages.toServer.userManagement;

import swp18e.messages.toServer.RequestMessageObject;

public class RegisterRequest extends RequestMessageObject {

    private String password;

    public RegisterRequest(String username, String password, Integer token){
        super(username, token);
        this.password = password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
