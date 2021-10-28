package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

class StubDeck implements DeckInterface
{
    private List<CardInterface> cards;

    @Override
    public List<CardInterface> draw(int count)
    {
        if(count == 0) return new ArrayList<>();
        cards = new ArrayList<>();
        cards.add(new FakeCard(new MockedGameCardTypeAction(0)));
        for(int i = 1; i < count; i++)
        {
            cards.add(new FakeCard(new MockedGameCardTypeNonAction(i)));
        }
        return new ArrayList<>(cards);
    }

    @Override
    public int getPoints() {
        return 0;
    }
}

class MockedGameCardTypeNonAction implements GameCardTypeInterface
{
    private int val;
    public MockedGameCardTypeNonAction(int val)
    {
        this.val = val;
    }
    @Override
    public int getPlusActions() {
        return 0;
    }
    @Override
    public int getPlusBuys() {
        return 0;
    }
    @Override
    public int getPlusCards() {
        return 0;
    }
    @Override
    public int getPlusCoins() {
        return 0;
    }
    @Override
    public int getPoints() {
        return 0;
    }
    @Override
    public int getCost() {
        return 0;
    }
    @Override
    public boolean isAction() {
        return false;
    }
    @Override
    public String getName() {
        return null;
    }
    @Override
    public String getDescription() {
        return null;
    }
    public int getVal()
    {
        return val;
    }
}

class MockedGameCardTypeAction extends MockedGameCardTypeNonAction
{
    public MockedGameCardTypeAction(int val)
    {
        super(val);
    }
    @Override
    public boolean isAction() {
        return true;
    }
}
public class HandTest
{
    private Hand h1;
    private List<CardInterface> cdl;
    private void setUp()
    {
        h1 = new Hand(new StubDeck());
    }

    @Test
    public void testDrawAndThrowAllWithInitialCards()
    {
        setUp();

        List<CardInterface> cdl = h1.throwAll();
        assertEquals(5,cdl.size());
        assertTrue(cdl.get(0).cardType().isAction());
        assertEquals(0,((MockedGameCardTypeAction)(cdl.get(0).cardType())).getVal());
        for(int i =1; i < cdl.size(); i++)
        {
            assertFalse(cdl.get(i).cardType().isAction());
            assertEquals(i,((MockedGameCardTypeNonAction)(cdl.get(i).cardType())).getVal());
        }
    }
    @Test
    public void testDrawAndThrowAllWithNewCards()
    {
        setUp();

        cdl = h1.throwAll();
        cdl = h1.throwAll();
        assertEquals(0,cdl.size());

        h1.draw(0);
        cdl = h1.throwAll();
        assertEquals(0,cdl.size());
        for(int j = 1; j < 100; j++)
        {
            h1.draw(j);

            cdl = h1.throwAll();
            assertEquals(j,cdl.size());
            assertTrue(cdl.get(0).cardType().isAction());
            assertEquals(0,((MockedGameCardTypeAction)(cdl.get(0).cardType())).getVal());
            for(int i = 1; i < cdl.size(); i++)
            {
                assertFalse(cdl.get(i).cardType().isAction());
                assertEquals(i,((MockedGameCardTypeNonAction)(cdl.get(i).cardType())).getVal());
            }
            cdl = h1.throwAll();
            assertEquals(0,cdl.size());
        }
    }
    @Test
    public void multipleDrawTest()
    {
        setUp();

        h1.throwAll();

        for(int i = 0; i < 6; i++)
        {
            h1.draw(i);
        }

        cdl = h1.throwAll();
        assertEquals(15, cdl.size());
    }
    @Test
    public void testIsInHandInitialCards()
    {
        setUp();

        for(int i = 0; i < 5; i++)
        {
            assertTrue(h1.isInHand(i));
        }
        for(int i = 6; i < 100; i++)
        {
            assertFalse(h1.isInHand(i));
        }
        for(int i = -1; i > -100; i--)
        {
            assertFalse(h1.isInHand(i));
        }
    }
    @Test
    public void testIsInHandNewCards()
    {
        setUp();

        h1.throwAll();
        for(int j = 0; j < 100; j++)
        {
            h1.draw(j);
            for(int i = 0; i < j; i++)
            {
                assertTrue(h1.isInHand(i));
            }
            for(int i = j; i < j+100; i++)
            {
                assertFalse(h1.isInHand(i));
            }
            for(int i = -1; i > -100; i--)
            {
                assertFalse(h1.isInHand(i));
            }
            h1.throwAll();
        }
    }
    @Test
    public void multipleDrawIsInHandTest()
    {
        setUp();

        h1.throwAll();

        for(int i = 0; i < 6; i++)
        {
            h1.draw(i);
        }

        for(int i = 0; i < 15; i++)
        {
            assertTrue(h1.isInHand(i));
        }
        assertFalse(h1.isInHand(16));
    }
    @Test
    public void isActionCardTest()
    {
        setUp();

        assertTrue(h1.isActionCard(0));
        for(int i = 1; i < 5; i++)
        {
            assertFalse(h1.isActionCard(i));
        }
        assertFalse(h1.isActionCard(6));
    }
    @Test
    public void testPlay()
    {
        setUp();

        Optional<CardInterface> c = h1.play(0);
        assertTrue(c.get().cardType().isAction());
        assertEquals(0,((MockedGameCardTypeAction)(c.get().cardType())).getVal());
        for(int i = 1; i < 5; i++)
        {
            c = h1.play(0);
            assertFalse(c.get().cardType().isAction());
            assertEquals(i,((MockedGameCardTypeNonAction)(c.get().cardType())).getVal());
        }
        assertFalse(h1.isActionCard(6));
        cdl = h1.throwAll();
        assertEquals(0, cdl.size());
    }

    @Test
    public void testPlayEmptyHand()
    {
        setUp();

        h1.throwAll();
        assertEquals(Optional.empty(), h1.play(0));
    }
    @Test
    public void testPlayEnd()
    {
        setUp();

        assertTrue(h1.play(4).isPresent());
        assertFalse(h1.play(4).isPresent());
    }
}
