package swp18e.messages.toClient;

public class InvitationResponse extends ResponseMessageObject {
    public InvitationResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
