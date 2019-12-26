package swp18e.messages.toServer.game;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.game.GameMessage;


public class EndRoundRequest extends GameMessage {

    public EndRoundRequest(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token, gameIdentifier);
    }
}
