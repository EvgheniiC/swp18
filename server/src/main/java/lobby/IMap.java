package lobby;

import com.google.common.eventbus.EventBus;
import game.Game;

import java.util.HashMap;

public interface IMap {

    EventBus getEventBus(int token);

    Lobby getLobby(int token);

    void setEventBus(int token, EventBus eventBus);

    void setLobby(int token, Lobby lobby);

    void removeEventbus(int token);

    void removeLobby(int token);

    Game getGame(int token);

    void setGame(int token, Game game);

    void removeGame(int token);

    boolean removeAll(int token);

    HashMap<Integer, Lobby> getAllLobbys();

}
