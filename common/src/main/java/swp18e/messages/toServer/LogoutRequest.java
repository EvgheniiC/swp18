package swp18e.messages.toServer;

public class LogoutRequest extends RequestMessageObject {


    public LogoutRequest(String username, Integer token) {
        super(username, token);
    }
}
