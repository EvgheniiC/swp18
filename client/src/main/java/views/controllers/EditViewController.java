package views.controllers;

import client.ClientInst;
import client.Verschluesselung;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import swp18e.messages.toClient.userManagement.EditResponse;
import swp18e.messages.toServer.userManagement.EditPasswordRequest;
import swp18e.messages.toServer.userManagement.EditUsernameRequest;

public class EditViewController {

    @FXML
    private TextField oldPasswordText;
    @FXML
    private TextField newPasswordText;
    @FXML
    private TextField confirmNewPasswordText;
    @FXML
    private TextField usernameText;
    @FXML
    private Label errorPasswordLabel;
    @FXML
    private Label errorConfirmLabel;
    @FXML
    private Label errorUsernameLabel;
    @FXML
    private Button usernameButton;
    @FXML
    private Button passwordButton;
    @FXML
    private Button cancelButton;

    public void initialize() {
        ClientInst.events.register(this);
    }

    @FXML
    private void usernameButtonClicked() {
        ClientInst.events.post(new EditUsernameRequest(ClientInst.username, usernameText.getText(), Verschluesselung.encryptPassword(oldPasswordText.getText()), ClientInst.token));
    }

    @FXML
    private void passwordButtonClicked() {

        if (checkPassword(newPasswordText.getText(), confirmNewPasswordText.getText())) {
            ClientInst.events.post(new EditPasswordRequest(ClientInst.username,  Verschluesselung.encryptPassword(oldPasswordText.getText()),
                    Verschluesselung.encryptPassword(newPasswordText.getText()), ClientInst.token));
        } else {
            errorConfirmLabel.setText("Passwörter stimmen nicht überein");
        }
    }

    @FXML
    private void cancelButtonClicked() {
        this.close();
    }

    private boolean checkPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    private void close() {
        ClientInst.events.unregister(this);
        ((Stage) oldPasswordText.getScene().getWindow()).close();
    }

    @Subscribe
    public void changeResult(EditResponse message) {
        if (message.getResult()) {
          Platform.runLater( () -> {
              ClientInst.username = message.getUsername();
              close();
          });
            Platform.runLater(() ->
                    close());
        } else {
            if (message.getReason().equals("Passwort ist falsch")) {
                Platform.runLater(() ->
                        errorPasswordLabel.setText("Das Passwort ist falsch"));
            } else if (message.getReason().equals("Das alte Passwort ist nicht korrekt")) {
                Platform.runLater(() ->
                        errorPasswordLabel.setText("Das Passwort ist falsch"));
            } else if (message.getReason().equals("Benutzer ist bereits vergeben")) {
                Platform.runLater(() ->
                        errorUsernameLabel.setText("Der Benutzername ist bereits vergeben"));
            }
        }
    }




}

