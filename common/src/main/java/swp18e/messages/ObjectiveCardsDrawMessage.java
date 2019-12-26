package swp18e.messages;

public class ObjectiveCardsDrawMessage extends MessageObject {
    private GameIdentifier gameIdentifier;

    public ObjectiveCardsDrawMessage(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }


    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
