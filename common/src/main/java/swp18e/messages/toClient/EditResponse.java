package swp18e.messages.toClient;

public class EditResponse extends ResponseMessageObject {



    public EditResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
