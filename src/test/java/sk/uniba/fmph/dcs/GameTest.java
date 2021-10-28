package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest
{
    private Game g1;
    private Game g2;
    private Game g3;
    private List<BuyDeckInterface> bds;
    private List<BuyDeckInterface> bds2;
    private void setUp()
    {
        bds = new LinkedList<>();
        bds2 = new LinkedList<>();

        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,1));

        g1 = new Game(new StubEndGameStrategy(false), bds, new GameCardFactory7c3e());
        g2 = new Game(new StubEndGameStrategy(false), bds2, new GameCardFactory7c3e());
        g3 = new Game(new StubEndGameStrategy(true), bds, new GameCardFactory7c3e());
    }

    @Test
    public void testGame()
    {
        setUp();
        assertTrue(g1.playCard(0));
        assertTrue(g2.playCard(0));

        assertFalse(g1.buyCard(0));
        assertFalse(g2.buyCard(0));

        assertTrue(g1.endPlayCardPhase());
        assertFalse(g1.endPlayCardPhase());
        assertTrue(g2.endPlayCardPhase());
        assertFalse(g2.endPlayCardPhase());

        assertFalse(g1.buyCard(0));
        assertTrue(g2.buyCard(0));

        assertFalse(g1.playCard(0));
        assertFalse(g2.playCard(0));

        assertTrue(g1.endTurn(new Couple<>()));
        assertTrue(g2.endTurn(new Couple<>()));
        assertTrue(g3.endTurn(new Couple<>()));

        assertFalse(g2.isGameOver());
        assertFalse(g1.isGameOver());
        assertTrue(g3.isGameOver());

        assertFalse(g3.playCard(0));
        assertFalse(g3.endPlayCardPhase());
        assertFalse(g3.buyCard(0));
        assertFalse(g3.endTurn(new Couple<>()));
    }
    @Test
    public void testEndTurnEarly()
    {
        setUp();
        assertTrue(g1.endTurn(new Couple<>()));
        assertTrue(g2.endTurn(new Couple<>()));
        assertTrue(g3.endTurn(new Couple<>()));
    }
    @Test
    public void testEndGame()
    {
        setUp();
        Couple<Boolean> cp = new Couple<>();
        Couple<Boolean> cp2 = new Couple<>();
        g3.endTurn(cp);
        g2.endTurn(cp2);
        assertTrue(cp.getFirst());
        assertFalse(cp2.getFirst());
    }
}
