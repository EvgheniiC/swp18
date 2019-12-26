package swp18e.messages.toServer;


public class CreateLobbyRequest extends RequestMessageObject {

    private String lobbyName;

    public CreateLobbyRequest(String username, Integer token, String lobbyName) {
        super(username, token);
        this.lobbyName = lobbyName;
    }

    public String getLobbyName() {
        return lobbyName;
    }
}
