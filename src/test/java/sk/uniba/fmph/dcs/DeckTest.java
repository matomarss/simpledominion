package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeckTest
{
    private Deck deck1;
    private Deck deck2;
    private Deck deck3;
    private Deck deck4;

    void setUp()
    {
        ArrayList<CardInterface> listForDiscardPile1 = new ArrayList<>();
        listForDiscardPile1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        listForDiscardPile1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));

        ArrayList<CardInterface> listForDiscardPile4 = new ArrayList<>();
        listForDiscardPile4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        listForDiscardPile4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_LABORATORY));

        ArrayList<CardInterface> listForDeck1 = new ArrayList<>();
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_PROVINCE));

        ArrayList<CardInterface> listForDeck4 = new ArrayList<>();
        listForDeck4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDeck4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDeck4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDeck4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDeck4.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));

        DiscardPile dp1 = new DiscardPile(listForDiscardPile1);
        DiscardPile dp2 = new DiscardPile(new ArrayList<>());
        DiscardPile dp3 = new DiscardPile(new ArrayList<>());
        DiscardPile dp4 = new DiscardPile(listForDiscardPile4);
        deck1 = new Deck(dp1, listForDeck1);

        deck2 = new Deck(dp4, new ArrayList<CardInterface>());

        deck3 = new Deck(dp2, new ArrayList<CardInterface>());

        deck4 = new Deck(dp3, listForDeck4);
    }

    @Test
    public void test_draw() {
        setUp();
        assertEquals(4,deck1.draw(5).size());
        assertEquals(2,deck2.draw(5).size());
        assertEquals(0,deck3.draw(5).size());
        assertEquals(5,deck4.draw(5).size());
    }

    @Test
    public void test_get_points() {
        setUp();
        assertEquals(7,deck1.getPoints());
        assertEquals(0,deck2.getPoints());
        assertEquals(0,deck3.getPoints());
        assertEquals(0,deck4.getPoints());
    }
}
