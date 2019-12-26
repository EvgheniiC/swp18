package views;

import client.ClientInst;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;
import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.gameLobby.AnswerInviteRequest;

public class PopUpWindow {



    public static Popup infoPopUp(Stage stage, String text){
        final Popup popUp = new Popup();
        int height = 100;
        int width = 200;
        System.out.println(stage.getTitle());
        Rectangle rec = new Rectangle(width, height);
        rec.setFill(Color.LIGHTBLUE);
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().add(new Text(text));
        textFlow.setLayoutX(15);
        textFlow.setLayoutY(10);
        Button button = new Button();
        button.setText("OK");
        button.setPrefSize(60, 30);
        button.setLayoutX(130);
        button.setLayoutY(60);
        button.setOnAction(event -> popUp.hide());
        popUp.getContent().addAll(rec, textFlow, button);

        popUp.setX(0);
        popUp.setY(0);
        popUp.show(stage);
        return popUp;
    }
    public static Popup invitePopUp(Stage stage, String inviteFrom, GameIdentifier gameIdentifier){
        final Popup popUp = new Popup();
        int height = 100;
        int width = 200;
        System.out.println(stage.getTitle());
        Rectangle rec = new Rectangle(width, height);
        rec.setFill(Color.LIGHTBLUE);
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().add(new Text("Neue Einladung von: "+inviteFrom));
        textFlow.setLayoutX(15);
        textFlow.setLayoutY(10);


        Button accept = new Button();
        accept.setText("Annehmen");
        accept.setPrefSize(90, 30);
        accept.setLayoutX(8);
        accept.setLayoutY(60);

        Button cancel = new Button();
        cancel.setText("Ablehnen");
        cancel.setPrefSize(90, 30);
        cancel.setLayoutX(102);
        cancel.setLayoutY(60);


        accept.setOnAction(event -> accept(inviteFrom, gameIdentifier, popUp));
        cancel.setOnAction(event -> cancel(inviteFrom, gameIdentifier, popUp));
        popUp.getContent().addAll(rec, textFlow, accept, cancel);

        popUp.setX(0);
        popUp.setY(0);
        popUp.show(stage);
        return popUp;
    }

    public static void accept(String origin, GameIdentifier gameIdentifier, Popup popup){
        popup.hide();
        ClientInst.events.post(new AnswerInviteRequest(ClientInst.username, ClientInst.token, origin, gameIdentifier, true));
        System.out.println("Accept");
    }

    public static void cancel(String origin, GameIdentifier gameIdentifier, Popup popup){
        popup.hide();
        ClientInst.events.post(new AnswerInviteRequest(ClientInst.username, ClientInst.token, origin, gameIdentifier, false));
        System.out.println("Cancel");
    }



}
