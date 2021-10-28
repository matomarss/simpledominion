package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandDeckIntegrationTest
{
    private Deck deck1;
    private Deck deck2;
    private Hand hand1;
    private Hand hand2;
    private DiscardPile discardPile;
    private DiscardPile discardPile2;
    private Deck deck3;
    private DiscardPile discardPile3;
    private Hand hand3;
    private DiscardPile discardPile4;
    private Deck deck4;

    private void setUp()
    {
        discardPile = new DiscardPile(new ArrayList<>());
        List<CardInterface> cdl2 = new ArrayList<>();
        cdl2.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl2.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl2.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl2.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl2.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        discardPile2 = new DiscardPile(cdl2);
        deck1 = new Deck(discardPile, new ArrayList<>());
        List<CardInterface> cdl = new ArrayList<>();
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        cdl.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        deck2 = new Deck(discardPile2,cdl);
        hand1 = new Hand(deck1);
        hand2 = new Hand(deck2);

        List<CardInterface> cdl3 = new ArrayList<>();
        cdl3.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl3.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl3.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl3.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        cdl3.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        discardPile3 = new DiscardPile(cdl3);
        discardPile4 = new DiscardPile(new ArrayList<>(cdl3));
        List<CardInterface> cdl4 = new ArrayList<>();
        cdl4.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl4.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl4.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl4.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl4.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        cdl4.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));

        deck3 = new Deck(discardPile3, cdl4);
        deck4 = new Deck(discardPile4, new ArrayList<>(cdl4));
        hand3 = new Hand(deck3);
    }

    private void assertIsType(CardInterface cd, GameCardType gct)
    {
        assertEquals(gct.getName(), cd.cardType().getName());
    }
    @Test
    public void testIntegratedDraw()
    {
        setUp();

        hand1.throwAll();
        hand2.throwAll();
        hand1.draw(5);
        hand2.draw(5);
        List<CardInterface> cdl = hand1.throwAll();
        List<CardInterface> cdl2 = hand2.throwAll();
        assertEquals(0,cdl.size());
        assertEquals(5,cdl2.size());

        hand3.throwAll();
        hand3.draw(10);
        List<CardInterface> cdl3 = hand3.throwAll();
        assertEquals(6,cdl3.size());
        for(int i =0; i < 5; i++)
        {
            assertIsType(cdl3.get(i),GameCardType.GAME_CARD_TYPE_LABORATORY);
        }
    }

    @Test
    public void testDrawTooMuch() {
        setUp();

        List<CardInterface> cdl = deck4.draw(11);

        assertEquals(11, cdl.size());
        for(int i =0; i < 5; i++)
        {
            assertIsType(cdl.get(i),GameCardType.GAME_CARD_TYPE_LABORATORY);
        }
    }
}
