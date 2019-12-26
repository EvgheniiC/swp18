package game.cards.wagonCard;

import color.Color;
import game.cards.DiscardPile;

public class WagonStackRun {
    public static void main(String[] args) {
        //Karte zum testen
        WagonCard wagonCard = new WagonCard(Color.BLUE);
        DiscardPile discardPile = new DiscardPile();
        discardPile.discardCard(wagonCard.getColor());
        WagonStack stack = new WagonStack();
        for (int y = 0; y < 10; y++) {
            for (int i = 0; i < 15; i++) {
                System.out.print(stack.drawCard().getColor());
                System.out.print("\t");

            }
            System.out.println(stack.drawCard().getColor());

        }
    }

}
