package swp18e.messages.toClient.updateMessage;

import javafx.util.Pair;
import swp18e.messages.GameIdentifier;

import java.util.ArrayList;
import java.util.List;

public class UpdateGameMessage extends  UpdateMessage{
    private List<Pair<Integer,String>> games;

    public UpdateGameMessage(List<Pair<Integer,String>> games) { this.games = games; }

    public UpdateGameMessage(GameIdentifier gameID){
        games = new ArrayList<Pair<Integer, String>>();
        games.add(new Pair<Integer, String>(gameID.getId(),gameID.getName()));
    }

    public List<Pair<Integer,String>> getGames() {
        return games;
    }

    public List<String> getNames(){
        List<String> names = new ArrayList<String>();
        for(Pair<Integer,String> id : games){
            names.add(id.getValue());
        }
        return names;
    }

    public void setGames(List<Pair<Integer,String>> games) {
        this.games = games;
    }
}
