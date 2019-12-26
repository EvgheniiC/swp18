package swp18e.messages.toClient;

public class LeaveLobbyResponse extends ResponseMessageObject {

    public LeaveLobbyResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
