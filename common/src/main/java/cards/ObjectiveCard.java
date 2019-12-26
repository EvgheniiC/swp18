package cards;

public class ObjectiveCard extends Cards {

    private String from;

    private String to;
    private int routePoints;
    public ObjectiveCard(String from, String to, int routePoints) {
        this.from = from;
        this.to = to;
        this.routePoints = routePoints;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getRoutePoints() {
        return routePoints;
    }
}


