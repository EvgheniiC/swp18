package views.controllers;

import client.ClientInst;
import client.Verschluesselung;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import swp18e.messages.toServer.userManagement.LoginRequest;
import swp18e.messages.toClient.userManagement.LoginSuccessfulResponse;
import swp18e.messages.toClient.userManagement.LoginResponse;
import views.ViewStart;
import user.AlreadyConnectedUsers;

public class LoginViewController {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private VBox vb_scene;


    public void initialize() {
        ClientInst.events.register(this);
        this.vb_scene.getStyleClass().add("login");
    }

    @FXML
    private void loginAction() {
        String username = usernameText.getText();
        String password = Verschluesselung.encryptPassword(passwordText.getText());
        LoginRequest msg = new LoginRequest(username, password, ClientInst.token);
        ClientInst.events.post(msg);
    }

    @FXML
    private void registerAction() {
        stop();
        ViewStart.startRegister();

    }

    @FXML
    private void changeErrorText(String text) {
        errorLabel.setText(text);
    }


    private void stop() {
        ClientInst.events.unregister(this);
        ((Stage) passwordText.getScene().getWindow()).close();
    }

    @Subscribe
    public void loginFail(LoginResponse message) {
        Platform.runLater(() -> changeErrorText(message.getReason()));
    }

    @Subscribe
    public void loginSuccsesfull(LoginSuccessfulResponse message) {
        Platform.runLater(() -> {
            stop();
            AlreadyConnectedUsers.alreadyConnectedUsers.setConnectedUsers(message.getActiveUser());
            ClientInst.username = message.getUsername();
            ViewStart.startLobby();
        });
    }

}

