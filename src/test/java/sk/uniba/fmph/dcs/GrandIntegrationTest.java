package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GrandIntegrationTest
{
    private Game game;
    private Game game2;
    private Game game3;
    private Game game4;
    private Game game5;

    List<BuyDeckInterface> bds3;
    private Game game6;

    private void setUp()
    {
        List<BuyDeckInterface> bds= new ArrayList<>();
        AtLeastNEmptyDecks aned = new AtLeastNEmptyDecks(bds, 2);
        GameCardFactory7c3e igcf = new GameCardFactory7c3e();
        game = new Game(aned,bds,igcf);

        List<BuyDeckInterface> bds2= new ArrayList<>();
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 10));
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 10));
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, 10));
        AtLeastNEmptyDecks aned2 = new AtLeastNEmptyDecks(bds2, 2);
        game2 = new Game(aned2,bds2,igcf);

        GameCardFactory gcf = new GameCardFactory(new GameCardType(0,0,0,0,0,0,true,"",""),10);
        game3 = new Game(aned2,bds2, gcf);
        game4 = new Game(aned2,bds2,new GameCardFactory(GameCardType.GAME_CARD_TYPE_MARKET,10));

        bds3= new ArrayList<>();
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1));
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1));
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1));
        AtLeastNEmptyDecks aned3 = new AtLeastNEmptyDecks(bds3, 2);
        game5 = new Game(aned3,bds3,igcf);

        game6 = new Game(aned3,bds3,new GameCardFactory(GameCardType.GAME_CARD_TYPE_COPPER, GameCardType.GAME_CARD_TYPE_ESTATE, 2,3));
    }

    private void testCardIdx(int cardIdx)
    {
        setUp();
        assertTrue(game.playCard(cardIdx));
    }

    @Test
    public void testPlayingCardRightIndex()
    {
        for (int i = 0; i < 5;i++)
        {
            testCardIdx(i);
        }
    }

    @Test
    public void testPlayingCardNotInHand()
    {
        setUp();
        assertFalse(game.playCard(6));
    }

    @Test
    public void testPlayingCardInRow()
    {
        setUp();

        for (int i = 0; i < 5;i++)
        {
            assertTrue(game.playCard(0));
        }

        assertFalse(game.playCard(0));
    }

    @Test
    public void testPlayActionCard()
    {
        setUp();

        assertTrue(game3.playCard(0));
        assertFalse(game3.playCard(0));

        game3.endTurn(new Couple<>());

        assertTrue(game3.playCard(0));
        assertFalse(game3.playCard(0));
    }

    @Test
    public void testPlayActionMarketCard()
    {
        setUp();

        for(int i = 0; i < 5; i++)
        {
            assertTrue(game4.playCard(0));
        }
    }

    @Test
    public void testEndingPlayPhase()
    {
        setUp();

        assertTrue(game.endPlayCardPhase());
        assertFalse(game.endPlayCardPhase());
    }

    @Test
    public void testEndingTurnAndPlayPhase()
    {
        setUp();

        assertTrue(game.endPlayCardPhase());
        assertTrue(game.endTurn(new Couple<>()));
        assertTrue(game.endPlayCardPhase());
    }

    @Test
    public void testBuyCardRightPhase()
    {
        setUp();

        assertFalse(game2.buyCard(0));
        game2.endPlayCardPhase();
        assertTrue(game2.buyCard(0));
    }

    @Test
    public void testBuyCardIsDeck()
    {
        setUp();

        assertFalse(game.buyCard(0));
        game.endPlayCardPhase();
        assertFalse(game.buyCard(0));
    }

    @Test
    public void testBuyEnoughCoins()
    {
        setUp();

        for(int i = 0; i < 5; i++)
        {
            game2.playCard(0);
        }
        game2.endPlayCardPhase();
        assertTrue(game2.buyCard(1));
    }

    @Test
    public void testBuyNotEnoughCoins()
    {
        setUp();

        game2.endPlayCardPhase();
        assertFalse(game2.buyCard(1));
    }

    @Test
    public void testBuyNotEnoughBuys()
    {
        setUp();

        game2.endPlayCardPhase();
        assertTrue(game2.buyCard(0));
        assertFalse(game2.buyCard(0));
    }

    @Test
    public void testBuyNotRightBuyDeck()
    {
        setUp();

        game2.endPlayCardPhase();
        assertFalse(game2.buyCard(5));
    }

    @Test
    public void testBuyEmptyingDeck()
    {
        setUp();

        game5.endPlayCardPhase();
        game5.buyCard(0);
        game5.endTurn(new Couple<>());
        game5.endPlayCardPhase();
        game5.buyCard(2);

        assertTrue(bds3.get(0).isEmpty());
        assertFalse(bds3.get(1).isEmpty());
        assertTrue(bds3.get(2).isEmpty());
    }

    @Test
    public void testEndGame()
    {
        setUp();

        assertFalse(game5.isGameOver());
        game5.endPlayCardPhase();
        game5.buyCard(0);
        game5.endTurn(new Couple<>());
        game5.endPlayCardPhase();
        game5.buyCard(2);

        Couple<Boolean> endOrWinner = new Couple<>();

        assertTrue(game5.endTurn(endOrWinner));
        assertTrue(game5.isGameOver());
        assertFalse(game5.endTurn(endOrWinner));
    }

    @Test
    public void testCardCycling()
    {
        setUp();

        int copperC = 0;
        int estateC = 0;
        List<String> cds = game6.getCardsInHand();
        for (int i = 0; i < 5; i++)
        {
            if(cds.get(i).equals("Copper")) copperC++;
            else if(cds.get(i).equals("Estate")) estateC++;
        }
        assertEquals(2,copperC);
        assertEquals(3,estateC);
        for (int i = 0; i < 5; i++)
        {
            game6.playCard(0);
        }
        game6.endTurn(new Couple<>());
        copperC = 0;
        estateC = 0;
        for (int i = 0; i < 5; i++)
        {
            if(cds.get(i).equals("Copper")) copperC++;
            else if(cds.get(i).equals("Estate")) estateC++;
        }
        assertEquals(2,copperC);
        assertEquals(3,estateC);
    }
}