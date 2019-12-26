package swp18e.messages.toServer.game;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.game.GameMessage;

public class ObjectiveCardsDrawRequest extends GameMessage {

    public ObjectiveCardsDrawRequest(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token, gameIdentifier);
    }

}
