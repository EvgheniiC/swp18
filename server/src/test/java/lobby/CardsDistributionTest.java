package lobby;

import cards.ObjectiveCard;
import gameStructure.WagonCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class CardsDistributionTest {

    private CardsDistribution cardsDistribution;
    private List<WagonCard> wagonCards = new ArrayList<>();
    private List<String> colorCards = new ArrayList<>();
    private List<ObjectiveCard> shortRoad = new ArrayList<>();
    private CardsDistribution distMock = Mockito.mock(CardsDistribution.class);

    @Before
    public void setUp(){
        cardsDistribution = new CardsDistribution();
        wagonCards.add(new WagonCard("red"));
        wagonCards.add(new WagonCard("black"));
        wagonCards.add(new WagonCard("red"));
        wagonCards.add(new WagonCard("green"));
        wagonCards.add(new WagonCard("joker"));

        colorCards.add("red");
        colorCards.add("black");
        colorCards.add("red");
        colorCards.add("green");
        colorCards.add("joker");

        shortRoad.add(new ObjectiveCard("Brest", "Petrograd", 10));
        shortRoad.add(new ObjectiveCard("Lisboa", "Danzig", 11));
        shortRoad.add(new ObjectiveCard("Palermo", "Moskva", 12));

    }

    @Test
    public void getCardsColors() {
        assertEquals(cardsDistribution.getCardsColors(wagonCards), colorCards);
    }

    @Test
    public void isDrawableStackShortRoad() {
        assertTrue(cardsDistribution.isDrawableStackShortRoad());
    }

    @Test
    public void isDrawableWagonStack() {
        assertTrue(cardsDistribution.isDrawableWagonStack(3));
    }

    @Test
    public void drawShortRoad() {
        when(distMock.drawShortRoad()).thenReturn(shortRoad);
        assertEquals(distMock.drawShortRoad(), shortRoad);
    }

    @Test
    public void drawWagonCards() {
        when(distMock.drawWagonCards(2)).thenReturn(wagonCards.subList(0, 1));
        assertEquals(distMock.drawWagonCards(2), wagonCards.subList(0, 1));
    }

    @Test
    public void getNumberJoker() {
        assertEquals(cardsDistribution.getNumberJoker(wagonCards), 1);
    }

    @Test
    public void drawOpenCards() {
        List<String> openCards = new ArrayList<>();
        when(distMock.drawOpenCards(openCards)).thenReturn(wagonCards);
        assertEquals(distMock.drawOpenCards(openCards), wagonCards);
    }
}