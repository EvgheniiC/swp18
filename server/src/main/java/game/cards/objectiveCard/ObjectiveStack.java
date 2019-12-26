package game.cards.objectiveCard;

import game.cards.CardStack;

import cards.ObjectiveCard;

import java.util.List;
import java.util.ArrayList;

public abstract class ObjectiveStack extends CardStack{

    protected List<ObjectiveCard> objectiveCards = new ArrayList<>();

    @Override
    public ObjectiveCard drawCard() {
        int index = (int) (Math.random() * objectiveCards.size());
        size--;
        ObjectiveCard card = objectiveCards.get(index);
        objectiveCards.remove(index);
        return card;
    }

}