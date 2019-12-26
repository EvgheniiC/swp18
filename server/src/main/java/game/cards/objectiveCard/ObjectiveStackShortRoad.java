package game.cards.objectiveCard;

import cards.ObjectiveCard;

public class ObjectiveStackShortRoad extends ObjectiveStack {

    //------------- Es gibt 40 Zielkarten mit normaler Strecke

    public ObjectiveStackShortRoad(){
        fillObjectiveList();
    }

    private void fillObjectiveList() {

        objectiveCards.add(new ObjectiveCard("Smolensk","Rostow",8));
        objectiveCards.add(new ObjectiveCard("Berlin","Rom", 9));
        objectiveCards.add(new ObjectiveCard("Kiev","St. Petersburg",6));
        objectiveCards.add(new ObjectiveCard("Paris","Zagreb",7));
        objectiveCards.add(new ObjectiveCard("Brüssel","Danzig",9));
        objectiveCards.add(new ObjectiveCard("Ankara","Charkiw",10));
        objectiveCards.add(new ObjectiveCard("Sarajevo","Sevastopol", 8));
        objectiveCards.add(new ObjectiveCard("Paris","Wien",8));
        objectiveCards.add(new ObjectiveCard("Stockholm","Wien",11));
        objectiveCards.add(new ObjectiveCard("Riga","Bukarest",10));
        objectiveCards.add(new ObjectiveCard("Madrid","Zürich",8));
        objectiveCards.add(new ObjectiveCard("Barcelona","Brüssel", 8));
        objectiveCards.add(new ObjectiveCard("Berlin","Moskau",12));
        objectiveCards.add(new ObjectiveCard("Brest","Marseille",7));
        objectiveCards.add(new ObjectiveCard("Frankfurt","Kopenhagen",5));
        objectiveCards.add(new ObjectiveCard("Essen","Kiev",10));
        objectiveCards.add(new ObjectiveCard("Rostow","Erzurum", 5));
        objectiveCards.add(new ObjectiveCard("Sofia","Izmir",5));
        objectiveCards.add(new ObjectiveCard("Athen","Ankara",5));
        objectiveCards.add(new ObjectiveCard("Zürich","Budapest",6));
        objectiveCards.add(new ObjectiveCard("Amsterdam","Vilnius",12));
        objectiveCards.add(new ObjectiveCard("Amsterdam","Pamplona", 7));
        objectiveCards.add(new ObjectiveCard("London","Wien",10));
        objectiveCards.add(new ObjectiveCard("London","Berlin",7));
        objectiveCards.add(new ObjectiveCard("Zürich","Brindisi",6));
        objectiveCards.add(new ObjectiveCard("Brest","venedig",8));
        objectiveCards.add(new ObjectiveCard("Edinburgh","Paris", 7));
        objectiveCards.add(new ObjectiveCard("Budapest","Sofia",5));
        objectiveCards.add(new ObjectiveCard("Marseille","Essen",8));
        objectiveCards.add(new ObjectiveCard("Athen","Vilnius",11));
        objectiveCards.add(new ObjectiveCard("Madrid","Dieppe",8));
        objectiveCards.add(new ObjectiveCard("Barcelona","München", 8));
        objectiveCards.add(new ObjectiveCard("Rom","Smyrna",8));
        objectiveCards.add(new ObjectiveCard("Zagreb","Brindisi",6));
        objectiveCards.add(new ObjectiveCard("Warschau","Smolensk",6));
        objectiveCards.add(new ObjectiveCard("Berlin","Bukarest",8));
        objectiveCards.add(new ObjectiveCard("Frankfurt","Smolensk", 13));
        objectiveCards.add(new ObjectiveCard("Kiev","Sotschi",8));
        objectiveCards.add(new ObjectiveCard("Palermo","Istanbul",8));
        objectiveCards.add(new ObjectiveCard("Venedig","Istanbul",10));

        size = objectiveCards.size();
    }

}
