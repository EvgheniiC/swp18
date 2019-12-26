package game.cards;

import color.Color;

public class DiscardPile {

    static int pinkCards = 0;
    static int whiteCards = 0;
    static int blueCards = 0;
    static int yellowCards = 0;
    static int orangeCards = 0;
    static int blackCards = 0;
    static int redCards = 0;
    static int greenCards = 0;
    static int jokerCards = 0;


    public boolean discardCard(Color color) {

        if (color.equals(Color.PINK)) {
            pinkCards++;
        } else if (color.equals(Color.WHITE)) {
            whiteCards++;
        } else if (color.equals(Color.BLUE)) {
            blueCards++;
        } else if (color.equals(Color.YELLOW)) {
            yellowCards++;
        } else if (color.equals(Color.ORANGE)) {
            orangeCards++;
        } else if (color.equals(Color.BLACK)) {
            blackCards++;
        } else if (color.equals(Color.RED)) {
            redCards++;
        } else if (color.equals(Color.GREEN)) {
            greenCards++;
        } else if (color.equals(Color.JOKER)) {
            jokerCards++;
        } else {
            return false;
        }
        return true;
    }

    public void clearPile() {
        pinkCards = 0;
        whiteCards = 0;
        blueCards = 0;
        yellowCards = 0;
        orangeCards = 0;
        blackCards = 0;
        redCards = 0;
        greenCards = 0;
        jokerCards = 0;

    }

    public static int getPinkCards() {
        return pinkCards;
    }

    public static int getWhiteCards() {
        return whiteCards;
    }

    public static int getBlueCards() {
        return blueCards;
    }

    public static int getYellowCards() {
        return yellowCards;
    }

    public static int getOrangeCards() {
        return orangeCards;
    }

    public static int getBlackCards() {
        return blackCards;
    }

    public static int getRedCards() {
        return redCards;
    }

    public static int getGreenCards() {
        return greenCards;
    }

    public static int getJokerCards() {
        return jokerCards;
    }
}
