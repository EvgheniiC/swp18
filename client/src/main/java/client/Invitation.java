package client;

import javafx.scene.control.*;

import java.util.Optional;

public class Invitation extends Dialog<Boolean>
{
    public Invitation(String originUser) {
        this.setTitle("Einladung");
        this.setHeaderText("Einladung von [" + originUser + "]");
        this.setContentText("Willst du mitspielen?");
        this.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        this.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType.YES) {
                return true;
            }
            else {
                return false;
            }
        });
    }

    public Optional<Boolean> showInvitation(){
        return this.showAndWait();
    }
}
