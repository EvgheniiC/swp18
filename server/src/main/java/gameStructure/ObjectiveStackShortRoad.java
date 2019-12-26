package gameStructure;

import cards.ObjectiveCard;

public class ObjectiveStackShortRoad extends ObjectiveStack {

    //------------- Es gibt 40 Zielkarten mit normaler Strecke

    public ObjectiveStackShortRoad(){
        fillObjectiveList();
    }

    private void fillObjectiveList() {

        //Nur zum Test
        objectiveCards.add(new ObjectiveCard("Paris","London",10));
        objectiveCards.add(new ObjectiveCard("Berlin","Macragge",Integer.MAX_VALUE));
        objectiveCards.add(new ObjectiveCard("Mars","Hamburg",10));
        objectiveCards.add(new ObjectiveCard("Nabu","London",100000000));
        objectiveCards.add(new ObjectiveCard("Paris","Jupiter",100000000));
        //Nur zum Test

        size = objectiveCards.size();
    }

}
