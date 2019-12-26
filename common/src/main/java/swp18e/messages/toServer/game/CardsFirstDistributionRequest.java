package swp18e.messages.toServer.game;

import swp18e.messages.GameIdentifier;

public class CardsFirstDistributionRequest extends GameMessage {

    public CardsFirstDistributionRequest(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token, gameIdentifier);
    }

}
