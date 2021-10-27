package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class AtLeastNEmptyDecksTest
{
    private AtLeastNEmptyDecks aed0;
    private AtLeastNEmptyDecks aed1;
    private AtLeastNEmptyDecks aed4;
    private ArrayList<BuyDeckInterface> bds1;

    private void setUp()
    {
        bds1 = new ArrayList<>();
        BuyDeck bd1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5);
        BuyDeck bd2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5);
        BuyDeck bd3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5);
        BuyDeck bd4 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5);
        bds1.add(bd1);bds1.add(bd2);bds1.add(bd3);bds1.add(bd4);
        aed0 = new AtLeastNEmptyDecks(bds1,0);
        aed1 = new AtLeastNEmptyDecks(bds1,1);
        aed4 = new AtLeastNEmptyDecks(bds1,4);
    }

    @Test
    public void TestIsGameOver()
    {
        setUp();
        assertTrue(aed0.isGameOver());
        assertFalse(aed1.isGameOver());
        assertFalse(aed4.isGameOver());
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,0));
        assertTrue(aed0.isGameOver());
        assertTrue(aed1.isGameOver());
        assertFalse(aed4.isGameOver());
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,0));
        assertTrue(aed0.isGameOver());
        assertTrue(aed1.isGameOver());
        assertFalse(aed4.isGameOver());
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,0));
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,0));
        assertTrue(aed0.isGameOver());
        assertTrue(aed1.isGameOver());
        assertTrue(aed4.isGameOver());
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER,0));
        assertTrue(aed0.isGameOver());
        assertTrue(aed1.isGameOver());
        assertTrue(aed4.isGameOver());
    }
}
