package swp18e.messages.toClient.updateMessage;

import cards.ObjectiveCard;
import swp18e.messages.GameIdentifier;

import java.util.List;

public class UpdateObjectiveCards extends UpdateMessage {

    private List<ObjectiveCard> objectiveCards;
    private GameIdentifier gameIdentifier;
    private String username;
    private int token;

    public UpdateObjectiveCards(String username, Integer token, GameIdentifier gameIdentifier,
                                List<ObjectiveCard> objectiveCards) {

        this.token = token;
        this.username = username;
        this.gameIdentifier = gameIdentifier;
        this.objectiveCards = objectiveCards;

    }

    public GameIdentifier getGameIdentifier(){
        return gameIdentifier;
    }

    public String getUsername() {
        return username;
    }

    public int getToken() {
        return token;
    }

    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }
}
