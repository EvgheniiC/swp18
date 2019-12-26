package swp18e.messages;

import java.io.Serializable;
import java.util.List;

public class LobbyMessage implements Serializable {

    private List<Integer> lobbyUserToken;
    private GameIdentifier gameIdentifier;


    public LobbyMessage(List<Integer> lobbyUserToken, GameIdentifier gameIdentifier){
        this.lobbyUserToken = lobbyUserToken;
        this.gameIdentifier = gameIdentifier;
    }

    public List<Integer> getLobbyUserToken(){
        return lobbyUserToken;
    }


    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
