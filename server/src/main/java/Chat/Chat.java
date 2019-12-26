package Chat;

import Server.*;
import com.google.common.eventbus.Subscribe;
import swp18e.messages.toServer.startMenu.ChatRequest;
import swp18e.messages.toClient.updateMessage.ChatUpdate;

public class Chat {

    private static Chat chat;

    public static void start (){
        chat = new Chat();
        Server.events.register(chat);
    }

    @Subscribe
    public void chatHandler (ChatRequest message ){
        Server.events.post(new ChatUpdate(message.getUsername(), message.getMessage()));
    }
}
