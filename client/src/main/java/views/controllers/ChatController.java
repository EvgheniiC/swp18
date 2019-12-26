package views.controllers;

import client.ClientInst;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import swp18e.messages.toServer.startMenu.ChatRequest;

import java.util.List;

public class ChatController {

    private final int MAX_CHAT_MESSAGES = 50;

    @FXML
    private ScrollPane chatScroll;
    @FXML
    private TextFlow chatText;
    @FXML
    private TextField chatInput;

    private EventBus events;

    public void initialize() {
        events = new EventBus("chat");
        events.register(this);
    }

    public EventBus getEvents() {
        return events;
    }

    public void setEvents(EventBus events) {
        this.events = events;
        events.register(this);
    }

    @FXML
    private void sendButtonClicked() {
        String message;
        message = chatInput.getText();
        chatInput.clear();
        ClientInst.events.post(new ChatRequest(ClientInst.username, message, ClientInst.token));
    }
    @FXML
    public void Enter(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)){
            sendButtonClicked();
        }
    }

    private void addChatMessage(String message) {
        List<Node> textList = chatText.getChildren();
        if (textList.size() > MAX_CHAT_MESSAGES) {
            textList.remove(0);
        }
        textList.add(new Text(message + "\n"));
        chatScroll.setVvalue(chatScroll.getVmax());
        chatScroll.setHvalue(chatScroll.getHmin());
    }


    @Subscribe
    public void chatReceive (String message){
        Platform.runLater(() -> addChatMessage(message));
    }
}
