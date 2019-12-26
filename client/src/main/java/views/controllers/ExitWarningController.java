package views.controllers;

import client.ClientInst;
import client.CloseConnectionClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ExitWarningController {

    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    public void initialize() {
        ClientInst.events.register(this);
    }

    @FXML
    private void yesClicked () {
        //todo logout Logik
        CloseConnectionClient.closeConnection(ClientInst.username);
        Platform.exit();
    }
    @FXML
    private void noClicked() {
        ClientInst.events.unregister(this);
        ((Stage) noButton.getScene().getWindow()).close();
    }

}
