package game.connections;

import game.Player;

import java.util.HashSet;

public class Connection {

    private String city1;
    private String city2;
    private int length;
    private String connectionColor;
    private HashSet<Player> owner;
    private int points;

    public boolean connectionTaken() {
        return false;
    }

    public void addOwner(Player owner) {
        return;
    }

    public void setOwner(HashSet<Player> owner) {
        this.owner = owner;
    }

    public HashSet<Player> getOwner() {
        return owner;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getCity2() {
        return city2;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setConnectionColor(String connectionColor) {
        this.connectionColor = connectionColor;
    }

    public String getConnectionColor() {
        return connectionColor;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
