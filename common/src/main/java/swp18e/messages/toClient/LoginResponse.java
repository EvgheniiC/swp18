package swp18e.messages.toClient;

public class LoginResponse extends ResponseMessageObject {


    public LoginResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
