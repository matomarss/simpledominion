package sk.uniba.fmph.dcs;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyDeckTest
{
    private BuyDeck bd1;
    private BuyDeck bd2;
    private BuyDeck bd3;
    private BuyDeck bd4;
    private void setUp()
    {
        bd1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 0);
        bd2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 5);

        bd3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY,2);
        bd4 = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 5);
    }

    private void assertIsEmpty(BuyDeck bd)
    {
        assertTrue(bd.isEmpty());
    }
    private void assertIsNotEmpty(BuyDeck bd)
    {
        assertFalse(bd.isEmpty());
    }
    private void assertCount(BuyDeck bd, int num)
    {
        assertEquals(num, bd.getCardsCount());
    }
    private void assertType(BuyDeck bd, GameCardType gct)
    {
        assertEquals(gct.getName(), bd.getCardsType().getName());
    }
    @Test
    public void TestGetCardsTypeCardsCountAndIsEmpty()
    {
        setUp();

        assertIsEmpty(bd1);
        assertCount(bd1,0);
        assertType(bd1, GameCardType.GAME_CARD_TYPE_COPPER);
        bd1.buy();
        assertIsEmpty(bd1);
        assertCount(bd1,0);
        assertType(bd1, GameCardType.GAME_CARD_TYPE_COPPER);

        assertIsNotEmpty(bd2);
        assertCount(bd2,5);
        assertType(bd2, GameCardType.GAME_CARD_TYPE_ESTATE);
        bd2.buy();
        assertIsNotEmpty(bd2);
        assertCount(bd2,4);
        assertType(bd2, GameCardType.GAME_CARD_TYPE_ESTATE);

        assertIsNotEmpty(bd3);
        assertCount(bd3,2);
        assertType(bd3, GameCardType.GAME_CARD_TYPE_LABORATORY);
        bd3.buy();
        assertIsNotEmpty(bd3);
        assertCount(bd3,1);
        assertType(bd3, GameCardType.GAME_CARD_TYPE_LABORATORY);
        bd3.buy();
        assertIsEmpty(bd3);
        assertCount(bd3,0);
        assertType(bd3, GameCardType.GAME_CARD_TYPE_LABORATORY);
    }

    @Test
    public void TestBuy()
    {
        setUp();
        int n = bd4.getCardsCount();
        for(int i = 1; i <= n; i++)
        {
            Optional<CardInterface> gc = bd4.buy();
            assertTrue(gc.isPresent());
            assertEquals(GameCardType.GAME_CARD_TYPE_FESTIVAL, gc.get().cardType());
            assertCount(bd4, n-i);
        }
        assertIsEmpty(bd4);

        Optional<CardInterface> gc2 = bd4.buy();
        assertFalse(gc2.isPresent());
        assertType(bd4, GameCardType.GAME_CARD_TYPE_FESTIVAL);
        assertIsEmpty(bd4);
    }
}
