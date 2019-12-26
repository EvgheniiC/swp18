package views.Events;

public class GameNameEvent {
    private String chosenGameName;

    public GameNameEvent(String chosenGameName) {
        this.chosenGameName = chosenGameName;
    }

    public String getChosenGameName() {
        return chosenGameName;
    }

    public void setChosenGameName(String chosenGameName) {
        this.chosenGameName = chosenGameName;
    }
}
