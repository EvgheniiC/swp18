package swp18e.messages.toClient.userManagement;

import swp18e.messages.toClient.ResponseMessageObject;

public class LogoutResponse extends ResponseMessageObject {


    public LogoutResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
