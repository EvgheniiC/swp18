package swp18e.messages.toClient.userManagement;

import swp18e.messages.toClient.ResponseMessageObject;

public class RegisterResponse extends ResponseMessageObject {


    public RegisterResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
