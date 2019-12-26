package swp18e.messages;

public class CardsFirstDistributionMessage extends MessageObject {

    private GameIdentifier gameIdentifier;

    public CardsFirstDistributionMessage(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }


    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
