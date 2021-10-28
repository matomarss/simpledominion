package sk.uniba.fmph.dcs;


import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TurnTest
{
    private Turn t1;
    private Turn t2;
    private TurnStatus ts1;
    private TurnStatus ts2;
    private List<BuyDeckInterface> bds;
    private List<BuyDeckInterface> bds2;
    private void setUp()
    {
        bds = new LinkedList<>();
        bds2 = new LinkedList<>();

        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,1));

        ts1 = new TurnStatus();
        ts2 = new TurnStatus();
        t1 = new Turn(ts1,bds,new GameCardFactory7c3e());
        t2 = new Turn(ts2,bds2,new GameCardFactory7c3e());
    }

    @Test
    public void testTurn()
    {
        setUp();

        t1.endTurn();
        assertEquals(1, ts1.actions);
        assertEquals(1, ts1.buys);
        assertEquals(0, ts1.coins);

        assertTrue(t1.playCard(0));
        assertTrue(t2.playCard(0));

        assertFalse(t1.buyCard(0));
        assertTrue(t2.buyCard(0));

        assertTrue(t1.playCard(0));
        assertTrue(t2.playCard(0));

        t1.endTurn();
        assertEquals(1, ts1.actions);
        assertEquals(1, ts1.buys);
        assertEquals(0, ts1.coins);
    }
}
