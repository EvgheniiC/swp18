package swp18e.messages.toClient.updateMessage;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toClient.updateMessage.UpdateMessage;

import java.util.List;

public class ActiveGamesUpdateRequest extends UpdateMessage {

    private List<GameIdentifier> gamesList;

    public List<GameIdentifier> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<GameIdentifier> gamesList) {
        this.gamesList = gamesList;
    }

}