package game.cards.objectiveCard;

import cards.ObjectiveCard;

public class ObjectiveStackRun {
    public static void main(String[] args) {
        ObjectiveStack stack = new ObjectiveStackShortRoad();
        ObjectiveCard card = stack.drawCard();
        System.out.println(card.getFrom() + "   " + card.getTo() + "   " + card.getRoutePoints());
    }
}
