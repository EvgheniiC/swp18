package lobby;

import com.google.common.eventbus.EventBus;
import game.Game;

import java.util.HashMap;

public class Map implements IMap{

    private HashMap<Integer, EventBus> eventBusList= new HashMap<>();
    private HashMap<Integer, Lobby> lobbyList = new HashMap<>();
    private HashMap<Integer, Game> gameList = new HashMap<>();


    @Override
    public EventBus getEventBus(int token) {
        return eventBusList.get(token);
    }
    @Override
    public Lobby getLobby(int token) {
        return lobbyList.get(token);
    }
    @Override
    public void setEventBus(int token, EventBus eventBus) {
        eventBusList.put(token, eventBus);
    }
    @Override
    public void setLobby(int token, Lobby lobby){
        lobbyList.put(token, lobby);

    }
    @Override
    public void removeEventbus(int token){
        eventBusList.remove(token);
    }
    @Override
    public void removeLobby(int token){
        lobbyList.remove(token);
    }
    @Override
    public Game getGame(int token) {
        return gameList.get(token);
    }
    @Override
    public void setGame(int token, Game game) {
        gameList.put(token, game);
    }

    @Override
    public void removeGame(int token){
        gameList.remove(token);
    }
    @Override
    public boolean removeAll(int token){
        if(gameList.containsKey(token) && lobbyList.containsKey(token) && eventBusList.containsKey(token)) {
            gameList.remove(token);
            lobbyList.remove(token);
            eventBusList.remove(token);
            return true;
        }else return false;
    }

    @Override
    public HashMap<Integer, Lobby> getAllLobbys(){
        return lobbyList;
    }
}
