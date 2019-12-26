package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;

import java.io.IOException;

public class ViewStart {


    public static void startLogin() {
        ViewStart.start("loginView", "Login", false, true);
    }

    public static void startLobby() {
        ViewStart.start("LobbyView", "Lobby", false, false);
    }

    public static void startRegister() {
        ViewStart.start("RegisterView", "Registrieren", false, true);
    }

    public static void startWarning() {
        ViewStart.start("ExitWarning", "Warnung", true, true);
    }

    public static void startEdit() {
        ViewStart.start("EditView", "Daten ändern", true, false);
    }

    public static void startGameNameView() {
        ViewStart.start("GameNameView", "Neues Spiel", true, false);
    }


    public static void startInvitation() { ViewStart.start("InviteView", "Einladung erhalten", true, true);
    }

    private static void start(String resource, String title, boolean isCloseable, boolean isBlocking) {
        try {
            Parent root = FXMLLoader.load(ViewStart.class.getResource("/javafx/fxml/" + resource + ".fxml"));
            Stage stage = new Stage();
            stage.setTitle(title);
            Scene scene = new Scene(root);
            if(resource.equals("loginView")){
                scene.getStylesheets().add(new File("loginStyle.css").toURI().toURL().toExternalForm());
            }

            stage.setScene(scene);
            if (isBlocking) {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
            if (!isCloseable) {
                stage.setOnCloseRequest(event -> {
                    startWarning();
                    event.consume();
                });
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
