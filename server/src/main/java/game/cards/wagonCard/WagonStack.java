package game.cards.wagonCard;

import color.Color;
import game.cards.DiscardPile;
import game.cards.CardStack;

public class WagonStack extends CardStack {


    private int numberOfPinkCards;
    private int cumulativeNumberOfPinkCards;

    private int numberOfWhiteCards;
    private int cumulativeNumberOfWhiteCards;

    private int numberOfBlueCards;
    private int cumulativeNumberOfBlueCards;

    private int numberOfYellowCards;
    private int cumulativeNumberOfYellowCards;

    private int numberOfOrangeCards;
    private int cumulativeNumberOfOrangeCards;

    private int numberOfBlackCards;
    private int cumulativeNumberOfBlackCards;

    private int numberOfRedCards;
    private int cumulativeNumberOfRedCards;

    private int numberOfGreenCards;
    private int cumulativeNumberOfGreenCards;

    private int numberOfJokerCards;
    private int cumulativeNumberOfJokerCards;

    public WagonStack() {
        defaultSetup();
    }

    private void defaultSetup() {
        size = 110;
        numberOfPinkCards = 12;
        numberOfWhiteCards = 12;
        numberOfBlueCards = 12;
        numberOfYellowCards = 12;
        numberOfOrangeCards = 12;
        numberOfBlackCards = 12;
        numberOfRedCards = 12;
        numberOfGreenCards = 12;
        numberOfJokerCards = 14;
    }

    @Override
    public WagonCard drawCard() {

        if (size == 0) {
            shuffleCards();
        }

        WagonCard card = createCard();


        Color color = card.getColor();
        if (color.equals(Color.PINK)) {
            numberOfPinkCards--;
        } else if (color.equals(Color.WHITE)) {
            numberOfWhiteCards--;
        } else if (color.equals(Color.BLUE)) {
            numberOfBlueCards--;
        } else if (color.equals(Color.YELLOW)) {
            numberOfYellowCards--;
        } else if (color.equals(Color.ORANGE)) {
            numberOfOrangeCards--;
        } else if (color.equals(Color.BLACK)) {
            numberOfBlackCards--;
        } else if (color.equals(Color.RED)) {
            numberOfRedCards--;
        } else if (color.equals(Color.GREEN)) {
            numberOfGreenCards--;
        } else if (color.equals(Color.JOKER)) {
            numberOfJokerCards--;
        }
        if (size != 0) {
            size--;
        }
        return card;
    }

    private void shuffleCards() {
        DiscardPile discardPile = new DiscardPile();
        numberOfPinkCards = discardPile.getPinkCards();
        numberOfWhiteCards = discardPile.getWhiteCards();
        numberOfBlueCards = discardPile.getBlueCards();
        numberOfYellowCards = discardPile.getYellowCards();
        numberOfOrangeCards = discardPile.getOrangeCards();
        numberOfBlackCards = discardPile.getBlackCards();
        numberOfRedCards = discardPile.getRedCards();
        numberOfGreenCards = discardPile.getGreenCards();
        numberOfJokerCards = discardPile.getJokerCards();
        size = numberOfPinkCards + numberOfWhiteCards + numberOfBlueCards + numberOfYellowCards + numberOfOrangeCards + numberOfBlackCards + numberOfRedCards + numberOfGreenCards + numberOfJokerCards;
        discardPile.clearPile();
    }

    private WagonCard createCard() {
        WagonCard card = new WagonCard(Color.BLUE);
        int cardNumber = (int) (Math.random() * size + 1);
        cumulativeCards();

        if (cardNumber <= cumulativeNumberOfPinkCards) {
            card.setColor(Color.PINK);
        } else if ((cardNumber <= cumulativeNumberOfWhiteCards)) {
            card.setColor(Color.WHITE);
        } else if ((cardNumber <= cumulativeNumberOfBlueCards)) {
            card.setColor(Color.BLUE);
        } else if ((cardNumber <= cumulativeNumberOfYellowCards)) {
            card.setColor(Color.YELLOW);
        } else if ((cardNumber <= cumulativeNumberOfOrangeCards)) {
            card.setColor(Color.ORANGE);
        } else if ((cardNumber <= cumulativeNumberOfBlackCards)) {
            card.setColor(Color.BLACK);
        } else if ((cardNumber <= cumulativeNumberOfRedCards)) {
            card.setColor(Color.RED);
        } else if ((cardNumber <= cumulativeNumberOfGreenCards)) {
            card.setColor(Color.GREEN);
        } else if (cardNumber <= cumulativeNumberOfJokerCards) {
            card.setColor(Color.JOKER);
        } else {
            card.setColor(Color.NO_CARDS_LEFT);
        }

        return card;
    }


    private void cumulativeCards() {
        cumulativeNumberOfPinkCards = numberOfPinkCards;
        cumulativeNumberOfWhiteCards = cumulativeNumberOfPinkCards + numberOfWhiteCards;
        cumulativeNumberOfBlueCards = cumulativeNumberOfWhiteCards + numberOfBlueCards;
        cumulativeNumberOfYellowCards = cumulativeNumberOfBlueCards + numberOfYellowCards;
        cumulativeNumberOfOrangeCards = cumulativeNumberOfYellowCards + numberOfOrangeCards;
        cumulativeNumberOfBlackCards = cumulativeNumberOfOrangeCards + numberOfBlackCards;
        cumulativeNumberOfRedCards = cumulativeNumberOfBlackCards + numberOfRedCards;
        cumulativeNumberOfGreenCards = cumulativeNumberOfRedCards + numberOfGreenCards;
        cumulativeNumberOfJokerCards = cumulativeNumberOfGreenCards + numberOfJokerCards;
    }
}