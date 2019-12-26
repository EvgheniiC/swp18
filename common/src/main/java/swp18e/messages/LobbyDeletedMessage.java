package swp18e.messages;

import java.util.List;

public class LobbyDeletedMessage extends LobbyMessage {

    public LobbyDeletedMessage(List<Integer>lobbyUserToken, GameIdentifier gameIdentifier) {
        super(lobbyUserToken,gameIdentifier);
    }

}
