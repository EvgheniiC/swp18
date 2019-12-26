package swp18e.messages.toServer.userManagement;

import swp18e.messages.toServer.RequestMessageObject;

public class LogoutRequest extends RequestMessageObject {


    public LogoutRequest(String username, Integer token) {
        super(username, token);
    }
}
