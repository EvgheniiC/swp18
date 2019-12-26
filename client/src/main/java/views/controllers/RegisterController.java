package views.controllers;

import client.ClientInst;
import client.Verschluesselung;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import swp18e.messages.toClient.userManagement.RegisterResponse;
import swp18e.messages.toServer.userManagement.RegisterRequest;
import views.ViewStart;


public class RegisterController{

    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField confirmPwdText;
    @FXML
    private Label errorLabel;

    public void initialize() {
        ClientInst.events.register(this);
    }


    @FXML
    private void eventCreateAccount(){

        if(isFieldsEmpty()){
            errorMessage("Bitte alle Pflichtfelder ausfüllen.");
        }else{
            errorMessage("");
            if (!isPasswordsEquals()){
                errorMessage("Das angegebene Passwort stimmte nicht mit seiner Bestätigung überein.");
            }else{
                errorMessage("");
                if (!isValidPassword()){
                    errorMessage("Das Passwort muss 6 - 20 Zeichen lang sein.");
                }else{
                    errorMessage("");
                    RegisterRequest msg = new RegisterRequest(usernameText.getText().trim(),
                            Verschluesselung.encryptPassword(passwordText.getText()), ClientInst.token);

                    ClientInst.events.post(msg);

                }
            }
        }
    }

    @FXML
    private void eventLogin(){
        close();
        ViewStart.startLogin();
    }


    //@return true, falls passwordText und confirmPwdText gleich sind
    private boolean isPasswordsEquals(){
        if(!passwordText.getText().trim().equals("")){
            return passwordText.getText().equals(confirmPwdText.getText());
        }
        return false;
    }


    //@return true, falls passwordText die richtige Länge hat
    private boolean isValidPassword(){
        if(!passwordText.getText().trim().equals("")){
            return passwordText.getText().length()>=6 && passwordText.getText().length()<=20;
        }
        return false;
    }


    //@return true, falls alle Felder ausgefüllt sind.
    private boolean  isFieldsEmpty(){
        return usernameText.getText().trim().equals("") || passwordText.getText().trim().equals("")
                || confirmPwdText.getText().trim().equals("");
    }


    @FXML
    private void errorMessage(String text){

        errorLabel.setText(text);
    }

    @FXML
    private void keyPressedPassword(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            eventCreateAccount();
        }
    }

    @FXML
    private void keyPressedPasswordConfirm(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            eventCreateAccount();
        }
    }


    public void close(){
        ClientInst.events.unregister(this);
        ((Stage)errorLabel.getScene().getWindow()).close();

    }

    @Subscribe
    public void resultHandler(RegisterResponse message) {
        if (message.getResult()) {
            Platform.runLater(() -> eventLogin());

        } else {
            Platform.runLater(() -> errorMessage(message.getReason()));
        }
    }


}