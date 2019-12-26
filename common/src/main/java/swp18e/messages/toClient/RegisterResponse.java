package swp18e.messages.toClient;

public class RegisterResponse extends ResponseMessageObject {


    public RegisterResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
