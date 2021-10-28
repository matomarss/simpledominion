package sk.uniba.fmph.dcs;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameCardTest
{
    private GameCard gc1;
    private GameCard gc2;
    private TurnStatus ts;

    private void setUp()
    {
        ts = new TurnStatus();
        ts.actions = 0;
        ts.buys = 0;
        ts.coins = 0;

        gc1 = new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE);
        gc2 = new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY);
    }

    private void assertType(GameCard gc, GameCardType gct)
    {
        assertEquals(gct.getName(), gc.cardType().getName());
    }
    private void assertTurnStatus(TurnStatus ts, int a, int b, int c)
    {
        assertEquals(a,ts.actions);
        assertEquals(b,ts.buys);
        assertEquals(c,ts.coins);
    }

    @Test
    public void testEvaluateAndCardType()
    {
        setUp();
        assertType(gc1,GameCardType.GAME_CARD_TYPE_ESTATE);
        assertEquals(GameCardType.GAME_CARD_TYPE_ESTATE.getPlusCards(), gc1.evaluate(ts));
        assertTurnStatus(ts, GameCardType.GAME_CARD_TYPE_ESTATE.getPlusActions(), GameCardType.GAME_CARD_TYPE_ESTATE.getPlusBuys(), GameCardType.GAME_CARD_TYPE_ESTATE.getPlusCoins());

        ts = new TurnStatus();
        ts.actions = 1;
        ts.buys = 2;
        ts.coins = 3;
        assertType(gc2,GameCardType.GAME_CARD_TYPE_LABORATORY);
        assertEquals(GameCardType.GAME_CARD_TYPE_LABORATORY.getPlusCards(), gc2.evaluate(ts));
        assertTurnStatus(ts, GameCardType.GAME_CARD_TYPE_LABORATORY.getPlusActions()+1, GameCardType.GAME_CARD_TYPE_LABORATORY.getPlusBuys()+2, GameCardType.GAME_CARD_TYPE_LABORATORY.getPlusCoins()+3);
    }
}
