package lobby;

import cards.ObjectiveCard;
import game.Player;
import swp18e.messages.GameIdentifier;
import color.Color;

import java.util.List;

public interface ICardsDistribution {
    void firstDistribution(Player player, GameIdentifier gameIdentifier);

    void objectiveCardsDrawAction(Player player, GameIdentifier gameIdentifier);

    boolean wagonCardsDrawAction(Player player, boolean isTunnelAction, int numberDraw, GameIdentifier gameIdentifier);

    void openCardsDrawAction(Lobby lobby, Player player, GameIdentifier gameIdentifier, List<Color> currentOpenCards);

    void discardPileAction(Player player, List<Color> wagonCards);

    //-------------------------

    boolean isDrawableStackShortRoad();

    boolean isDrawableWagonStack(int k);

    List<ObjectiveCard> drawShortRoad();

    List<Color> drawWagonCards(int numberDraw);

    int getNumberJoker(List<Color> wagonCardsColors);

    void discardCardAll(List<Color> wagonCardsColors);

    List<Color> drawOpenCards(List<Color> openCards);
}
