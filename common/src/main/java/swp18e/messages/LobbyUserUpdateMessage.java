package swp18e.messages;

import java.util.List;

public class LobbyUserUpdateMessage extends LobbyMessage {
    private List<String> lobbyUsernames;

    public LobbyUserUpdateMessage(GameIdentifier gameIdentifier,List<Integer>lobbyUserToken, List<String> lobbyUsernames) {
        super(lobbyUserToken,gameIdentifier);
        this.lobbyUsernames = lobbyUsernames;
    }

    public List<String> getLobbyUsernames() {
        return lobbyUsernames;
    }
}
