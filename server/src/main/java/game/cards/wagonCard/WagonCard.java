package game.cards.wagonCard;

import cards.Cards;
import color.Color;

public class WagonCard extends Cards {
    private Color color;

    public WagonCard(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
