package game.cards.objectiveCard;

import cards.ObjectiveCard;

public class ObjectiveStackLongRoad extends ObjectiveStack {

    //------------- Es gibt 6 Zielkarten mit langer Strecke

    public ObjectiveStackLongRoad(){
        //Nur für den Test
        objectiveCards.add(new ObjectiveCard("Brest", "St. Petersburg", 20));
        objectiveCards.add(new ObjectiveCard("Lissabon", "Danzig", 20));
        objectiveCards.add(new ObjectiveCard("Palermo", "Moskau", 20));
        objectiveCards.add(new ObjectiveCard("Cádiz", "Stockholm", 21));
        objectiveCards.add(new ObjectiveCard("Edinburgh", "Athen", 21));
        objectiveCards.add(new ObjectiveCard("Kopenhagen", "Erzurum", 21));
        size = objectiveCards.size();
    }

}
