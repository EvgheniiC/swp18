package swp18e.messages;

import java.util.List;

public class DiscardPileMessage extends MessageObject {

    private GameIdentifier gameIdentifier;
    private List<String> wagonCards;

    public DiscardPileMessage(String username, Integer token, GameIdentifier gameIdentifier, List<String> wagonCards) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
        this.wagonCards = wagonCards;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }

    public List<String> getWagonCards() {
        return wagonCards;
    }
}
