package swp18e.messages;

import cards.ObjectiveCard;

import java.util.List;

public class UpdateObjectiveCards extends GameMessage {

    private List<ObjectiveCard> objectiveCards;

    public UpdateObjectiveCards(String playerUsername, Integer playerToken, GameIdentifier gameIdentifier,
                                List<ObjectiveCard> objectiveCards) {

        super(playerUsername, playerToken, gameIdentifier);
        this.objectiveCards = objectiveCards;

    }

    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }
}
