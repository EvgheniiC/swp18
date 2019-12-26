package swp18e.messages.toClient;

public class LogoutResponse extends ResponseMessageObject {


    public LogoutResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
