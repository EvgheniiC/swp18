package game.cards;

import cards.Cards;

public abstract class CardStack {

    protected int size;

    public abstract Cards drawCard();

    public int getSize(){
        return size;
    }
}