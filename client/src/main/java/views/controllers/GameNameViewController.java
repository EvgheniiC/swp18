package views.controllers;

import client.ClientInst;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.Events.GameNameEvent;

public class GameNameViewController {

    @FXML
    private TextField nameText;

    public void initialize() {
        ClientInst.events.register(this);
    }

    @FXML
    private void okButtonClicked() {
        ClientInst.events.post(new GameNameEvent(nameText.getText()));
        stop();
    }

    @FXML
    private void cancelButtonClicked() {
        stop();
    }

    private void stop() {
        ClientInst.events.unregister(this);
        ((Stage)nameText.getScene().getWindow()).close();
    }

}
