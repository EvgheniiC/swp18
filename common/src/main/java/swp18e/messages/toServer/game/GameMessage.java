package swp18e.messages.toServer.game;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.RequestMessageObject;

//nicht wieder umbenennen
public class GameMessage extends RequestMessageObject {

    private GameIdentifier gameIdentifier;

    public GameMessage(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
