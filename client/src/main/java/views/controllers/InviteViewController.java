package views.controllers;

import client.ClientInst;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.gameLobby.AnswerInviteRequest;
import views.Events.InviteEvent;

public class InviteViewController {

    @FXML
    private Label invitingPlayerLabel;
    @FXML
    private Label invitedGameLabel;
    @FXML
    private Button acceptButton;
    @FXML
    private Button declineButton;

    private String originUser;
    private GameIdentifier gameIdentifier;

    public void initialize() {
        ClientInst.events.register(this);
    }

    @FXML
    private void changeInvitingPlayerLabel (String invitingPlayer) {
        invitingPlayerLabel.setText(invitingPlayer);
    }
    @FXML
    private void changeInvitedGameLabel (GameIdentifier gameIdentifier) {
        invitedGameLabel.setText(gameIdentifier.getName() + ", " + gameIdentifier.getId());
    }

    @Subscribe
    public void receiveInvite(InviteEvent message) {
        originUser = message.getOriginUser();
        gameIdentifier = message.getGameIdentifier();
        Platform.runLater(() -> {
            changeInvitingPlayerLabel(originUser);
            changeInvitedGameLabel(message.getGameIdentifier());
        });
    }

    @FXML
    private void acceptClicked () {
        boolean answer = true;
        AnswerInviteRequest msg = new AnswerInviteRequest(ClientInst.username, ClientInst.token, originUser, gameIdentifier, answer);
        ClientInst.events.post(msg);

        stop();
    }

    @FXML
    private void declineClicked () {
        boolean answer = false;
        AnswerInviteRequest msg = new AnswerInviteRequest(ClientInst.username, ClientInst.token, originUser, gameIdentifier, answer);
        ClientInst.events.post(msg);

        stop();
    }

    private void stop() {
        ClientInst.events.unregister(this);
        ((Stage) invitingPlayerLabel.getScene().getWindow()).close();
    }

}
