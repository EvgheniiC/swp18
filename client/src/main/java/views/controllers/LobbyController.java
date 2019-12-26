package views.controllers;

import client.ClientInst;
import client.Invitation;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import swp18e.messages.*;
import swp18e.messages.toClient.gameLobby.CreateLobbyResponse;
import swp18e.messages.toClient.gameLobby.InvitationResponse;
import swp18e.messages.toClient.updateMessage.ActiveGamesUpdateRequest;
import swp18e.messages.toClient.updateMessage.ChatUpdate;
import swp18e.messages.toClient.updateMessage.UpdateGameMessage;
import swp18e.messages.toClient.updateMessage.UpdateUserMessage;
import swp18e.messages.toServer.gameLobby.AnswerInviteRequest;
import swp18e.messages.toServer.gameLobby.CreateLobbyRequest;
import swp18e.messages.toServer.gameLobby.InviteLobbyRequest;
import swp18e.messages.toServer.userManagement.LogoutRequest;
import user.AlreadyConnectedUsers;
import views.Events.GameNameEvent;
import views.Events.ListViewEvent;

import views.ViewStart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyController {

    @FXML
    private BorderPane lobbyPane;
    @FXML
    private BorderPane lobbyRight;
    @FXML
    private BorderPane lobbyLeft;
    @FXML
    private Button startButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button editButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button joinButton;
    @FXML
    private Button inviteButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Parent userList;
    @FXML
    private ListController userListController;
    @FXML
    private Parent gamesList;
    @FXML
    private ListController gamesListController;
    @FXML
    private Parent chatView;
    @FXML
    private ChatController chatViewController;

    private HashMap<String, GameIdentifier> activeGames;
    private GameIdentifier selectedGame;
    private String selectedUser;
    private List<Pair<Integer, String>> users;

    public static boolean isLobbyCreator = false;
    public static boolean isInvitedUser = false;
    public static GameIdentifier gameID = null;

    public void initialize() {
        activeGames = new HashMap<String, GameIdentifier>();
        users = new ArrayList<Pair<Integer, String>>();
        selectedGame = null;
        selectedUser = null;
        ClientInst.events.register(this);
        userListController.getEvents().register(this);
        userListController.setListType(ListController.USER_TYPE);
        gamesListController.getEvents().register(this);
        gamesListController.setListType(ListController.GAME_TYPE);
        chatViewController.getEvents().register(this);
        addAlreadyConnectedUsers();
        usernameLabel.setText(ClientInst.username);
    }

    @FXML
    private void startButtonClicked() {
        ViewStart.startGameNameView();
    }

    @FXML
    private void logoutButtonClicked() {
        ClientInst.events.post(new LogoutRequest(ClientInst.username, ClientInst.token));
        stop();
        ViewStart.startLogin();
    }

    @FXML
    private void joinButtonClicked() {
        if (selectedGame != null) {
            ClientInst.events.post(new AnswerInviteRequest(ClientInst.username, ClientInst.token, null, selectedGame, true));
        }
    }

    @FXML
    private void editButtonClicked() {
        ViewStart.startEdit();
    }

    @FXML
    private void exitButtonClicked() {
        ViewStart.startWarning();
    }

    @FXML
    private void inviteButtonClicked() {
        if (this.selectedUser != null) {
            if(LobbyController.isLobbyCreator) {
                if (this.selectedUser.equals(ClientInst.username + "\n")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Einladung problemm");
                    alert.setContentText("Du kannst nicht sich selbst einladen");
                    alert.showAndWait();
                    return;
                }

                ClientInst.events.post(new InviteLobbyRequest(ClientInst.username, ClientInst.token, this.selectedUser.substring(0,this.selectedUser.length() - 1), LobbyController.gameID));
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
    }

    private void stop() {
        ClientInst.events.unregister(this);
        ((Stage) lobbyPane.getScene().getWindow()).close();
    }

    private void addAlreadyConnectedUsers() {
        userListController.getEvents().post(AlreadyConnectedUsers.alreadyConnectedUsers.getConnectedUsers());
    }

    @Subscribe
    public void onlineUserChanged(UpdateUserMessage message) {
        Platform.runLater(() ->
                userListController.getEvents().post(message.getUsers()));
    }

    @Subscribe
    public  void activeGamesChanged(UpdateGameMessage message){
        for(Pair<Integer,String> g : message.getGames()){
            activeGames.put(g.getValue(), new GameIdentifier(g.getKey(),g.getValue()));
        }
        Platform.runLater(() ->
                gamesListController.getEvents().post(message.getNames()));
    }

    @Subscribe
    public void listObjectSelected(ListViewEvent message) {
        if (message.getType() == ListController.GAME_TYPE) {
            selectedGame = activeGames.get(message.getMessage());
        }
        if (message.getType() == ListController.USER_TYPE) {
            selectedUser = message.getMessage();
        }
    }

    /*@Subscribe
    public void activeGamesChanged(ActiveGamesUpdateRequest message) {
        List<String> gameNames = new ArrayList<>();
        for (GameIdentifier game : message.getGamesList()) {
            activeGames.put(game.getName(), game);
            gameNames.add(game.getName());
        }
        gamesListController.getEvents().post(gameNames);
    }*/

    @Subscribe
    public void chatReceive(ChatUpdate evt) {
        chatViewController.getEvents().post("[" + evt.getUsername() + "] " + evt.getMessage());
    }

    @Subscribe
    public void gameNameDecided(GameNameEvent evt) {///////////////////////////////////////////
        if(gameID == null){
            ClientInst.events.post(new CreateLobbyRequest(ClientInst.username, ClientInst.token, evt.getChosenGameName()));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Lobby creation problem");
            alert.setContentText("You have already created lobby [" + gameID.getName() + "]");
            alert.showAndWait();
        }
    }

    @Subscribe
    public void gameCreated(CreateLobbyResponse message){///////////
        gameID = message.getGameIdentifier();
        isLobbyCreator = true;
        users.add(new Pair<Integer, String>(ClientInst.token, ClientInst.username));
    }

    @Subscribe
    public void invitationReceived(InvitedMessage message){
        Platform.runLater(()->{
            onInvitationReceived(message);
        });
    }

    private void onInvitationReceived(InvitedMessage message) {
        gameID = message.getGameIdentifier();

        Invitation invitation = new Invitation(message.getOriginUser());
        invitation.showInvitation();

        if(invitation.getResult()) {
            ClientInst.events.post(new AnswerInviteRequest(ClientInst.username, ClientInst.token, message.getOriginUser(), gameID, true));

        }
        else{
            ClientInst.events.post(new AnswerInviteRequest(ClientInst.username, ClientInst.token, message.getOriginUser(), gameID, false));
        }
    }

    @Subscribe
    public void userJoined(InvitationResponse message){
        if(message.getResult()){
            users.add(new Pair<Integer, String>(message.getToken(), message.getUsername()));
            userListController.addJoinedUser(message.getUsername());
        }
    }

    /*@Subscribe
    public void receiveInvite(InviteLobbyRequest message) {
        if (message.getTargetUser().equals(ClientInst.username)) {
            ViewStart.startInvitation();
        }
    }*/


}
