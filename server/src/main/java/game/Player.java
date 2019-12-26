package game;

import cards.ObjectiveCard;
import color.Color;
import game.cards.wagonCard.WagonCard;
import userManagement.UserC;
import java.util.ArrayList;
import game.connections.Connection;
import java.util.HashSet;
import java.util.List;

public class Player {

    private HashSet<Connection> connection;
    private List<WagonCard> wagonCards;
    private List<ObjectiveCard> objectiveCards;
    private Color playerColor;
    private int playerNumber;
    private int stationsLeft;
    private UserC user;
    private int stationsBuild;
    private int trainsLeft;
    private boolean isTurn = false;

    public Player(){
        wagonCards = new ArrayList<>();
        objectiveCards = new ArrayList<>();
    }



    public List<WagonCard> getWagonCards() {
        return wagonCards;
    }

    public void addWagonCards(List<Color> wagonCardsColors){
        if(wagonCardsColors != null){
            for(Color color: wagonCardsColors){
                wagonCards.add(new WagonCard(color));
            }
        }
    }

    //nur um die gespielte Wagonkarte zu löschen
    public void removeWagonCards(List<Color> wagonCardsColors){
        if(wagonCards != null && wagonCardsColors != null){
            for(Color color: wagonCardsColors){
                removeWagonCard(color);
            }
        }
    }

    private boolean removeWagonCard(Color color){
        if(wagonCards != null){
            for(WagonCard wCard: wagonCards){
                if(wCard.getColor().equals(color)){
                    wagonCards.remove(wCard);
                    return true;
                }
            }
        }
        return false;
    }

    public void setConnection(HashSet<Connection> connection) {
        this.connection = connection;
    }

    public HashSet<Connection> getConnection() {
        return connection;
    }

    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }

    public void addObjectiveCards(List<ObjectiveCard> objectiveCards){
        this.objectiveCards.addAll(objectiveCards);
    }

    //nur um die Zielkarten, die player nicht möchte, zu löschen
    public boolean removeObjectiveCards(List<ObjectiveCard> objectiveCards){
        //TODO: die Zielkarten, die player nicht möchte, löschen
        return this.objectiveCards.removeAll(objectiveCards);
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setStationsLeft(int stationsLeft) {
        this.stationsLeft = stationsLeft;
    }

    public int getStationsLeft() {
        return stationsLeft;
    }

    public void setUser(UserC user) {
        this.user = user;
    }

    public UserC getUser() {
        return user;
    }

    public void setStationsBuild(int stationsBuild) {
        this.stationsBuild = stationsBuild;
    }

    public int getStationsBuild() {
        return stationsBuild;
    }

    public void setTrainsLeft(int trainsLeft) {
        this.trainsLeft = trainsLeft;
    }

    public int getTrainsLeft() {
        return trainsLeft;
    }

    public void addConnection(Connection connection) {
    }


    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;

    }
}