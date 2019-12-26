package swp18e.messages.toClient.game;

import color.Color;
import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.game.GameMessage;

import java.util.List;

public class DiscardPileMessage extends GameMessage {

    private List<Color> wagonCards;

    public DiscardPileMessage(String username, Integer token, GameIdentifier gameIdentifier, List<Color> wagonCards) {
        super(username, token, gameIdentifier);
        this.wagonCards = wagonCards;
    }

    public List<Color> getWagonCards() {
        return wagonCards;
    }
}
