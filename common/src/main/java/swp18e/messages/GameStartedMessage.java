package swp18e.messages;

import java.util.List;

public class GameStartedMessage extends LobbyMessage {


    public GameStartedMessage(List<Integer> lobbyUserToken, GameIdentifier gameIdentifier) {
        super(lobbyUserToken, gameIdentifier);
    }
}
