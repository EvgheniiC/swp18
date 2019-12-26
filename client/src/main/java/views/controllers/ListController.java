package views.controllers;

import client.ClientInst;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import swp18e.messages.toServer.gameLobby.InviteLobbyRequest;
import views.Events.ListViewEvent;

import java.util.ArrayList;
import java.util.List;

public class ListController {

    public static final int USER_TYPE = 1;
    public static final int GAME_TYPE = 2;
    static int eventBusId = 0;

    @FXML
    private TextField searchText;
    @FXML
    private Button searchButton;
    @FXML
    private ScrollPane listScrollPane;
    @FXML
    private TextFlow listTextFlow;

    private List<String> allElements;
    private int listType;
    private EventBus events;
    private List<String> invitedUsers;
    private List<String> joinedUsers;

    public void initialize() {
        joinedUsers = new ArrayList<String>();
        invitedUsers = new ArrayList<String>();
        listType = 0;
        events = new EventBus("list" + eventBusId++);
        events.register(this);
        allElements = new ArrayList<>();
    }

    @FXML
    private void searchButtonClicked() {
        String search = searchText.getText();
        if (search == null || search.equals("")) {
            refreshList(allElements);
        } else {
            List<String> elements = allElements;
            List<String> filteredList = Lists.newArrayList(Collections2.filter(elements, Predicates.containsPattern(search)));
            refreshList(filteredList);
        }
    }

    @FXML
    private void searchTyped(KeyEvent keyEvent) {
        searchButtonClicked();
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    private void resetChoice() {
        for (Node text : listTextFlow.getChildren()) {
            ((Text) text).setFill(Color.BLACK);
            ((Text) text).setUnderline(false);
            events.post(new ListViewEvent(listType, null));
        }
    }

    private void refreshList(List<String> elements) {
        listTextFlow.getChildren().clear();
        for (String element : elements) {
            Text text = new Text(element + "\n");
            if(listType == ListController.USER_TYPE) {
                for (String str : invitedUsers) {
                    if (text.getText().equals(str + "\n"))
                        text.setFill(Color.CORAL);
                }
                for (String str : joinedUsers) {
                    if (text.getText().equals(str + "\n"))
                        text.setFill(Color.DARKGREEN);
                }
            }
            listTextFlow.getChildren().add(text);
            text.setOnMouseClicked(evt -> {
                resetChoice();
                text.setFill(Color.HOTPINK);
                text.setUnderline(true);
                events.post(new ListViewEvent(listType, text.getText()));
            });
            if(listType == ListController.USER_TYPE) {
                text.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                    @Override
                    public void handle(ContextMenuEvent event) {
                        ContextMenu cm = new ContextMenu();
                        String sourceText = ((Text) event.getSource()).getText();
                        String targetUserName = sourceText.substring(0, sourceText.length() - 1);

                        MenuItem inviteMenuItem = new MenuItem("Einladung user");
                        inviteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                onInviteMenuItemClick(targetUserName, text);
                            }
                        });

                        cm.getItems().add(inviteMenuItem);
                        cm.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
                    }
                });
            }
        }
    }

    public void setEventBus(EventBus bus) {
        this.events = bus;
        bus.register(this);
    }

    public EventBus getEvents() {
        return events;
    }

    @Subscribe
    public void elementsChanged(List<String> elements) {
        Platform.runLater(() -> {
            allElements = elements;
            refreshList(elements);
        });
    }

    private void onInviteMenuItemClick(String targetUserName, Text text)
    {
        if(LobbyController.isLobbyCreator) {
            if (targetUserName.equals(ClientInst.username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Einladung problemm");
                alert.setContentText("Du kannst nicht sich selbst einladen");
                alert.showAndWait();
                return;
            }

            ClientInst.events.post(new InviteLobbyRequest(ClientInst.username, ClientInst.token, targetUserName, LobbyController.gameID));
            invitedUsers.add(targetUserName);
            text.setFill(Color.CORAL);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Einladung problemm");
            alert.setContentText("Zuerst ein Spiel erstellen,dann einladen");
            alert.showAndWait();
            return;
        }
    }

    public List<String> getAllElements(){
        return allElements;
    }

    public void addJoinedUser(String userName)
    {
        invitedUsers.remove(userName);
        joinedUsers.add(userName);
        Platform.runLater(() -> {
            refreshList(allElements);
        });
    }
}
