package swp18e.messages;

import cards.ObjectiveCard;

import java.util.List;

public class UpdateCardsFirstDistribution extends GameStartedMessage {

    private List<String> wagonCards;
    private List<ObjectiveCard> objectiveCards;

    public UpdateCardsFirstDistribution(String username, Integer token, GameIdentifier gameIdentifier,
                                        List<ObjectiveCard> objectiveCards, List<String> wagonCards) {

        super(username, token, gameIdentifier);
        this.objectiveCards = objectiveCards;
        this.wagonCards = wagonCards;
    }

    public List<String> getWagonCards() {
        return wagonCards;
    }


    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }

}
