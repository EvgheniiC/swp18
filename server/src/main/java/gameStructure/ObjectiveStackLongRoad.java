package gameStructure;

import cards.ObjectiveCard;

public class ObjectiveStackLongRoad extends ObjectiveStack {

    //------------- Es gibt 6 Zielkarten mit langer Strecke

    public ObjectiveStackLongRoad(){
        //Nur für den Test
        objectiveCards.add(new ObjectiveCard("Brest", "Petrograd", 20));
        objectiveCards.add(new ObjectiveCard("Lisboa", "Danzig", 20));
        objectiveCards.add(new ObjectiveCard("Palermo", "Moskva", 20));
        objectiveCards.add(new ObjectiveCard("Cadiz", "Stockholm", 21));
        objectiveCards.add(new ObjectiveCard("Edinburgh", "Athina", 21));
        objectiveCards.add(new ObjectiveCard("Kobenhavn", "Erzurum", 21));
        size = objectiveCards.size();
    }

}
