package sk.uniba.fmph.dcs;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayTest
{
    private Play p1;
    private Play p2;

    private void setUp()
    {
        p1 = new Play();
        p2 = new Play();
    }

    private void assertEmpty(Play p)
    {
        assertTrue(p.throwAll().isEmpty());
    }
    private void assertNotEmpty(Play p)
    {
        assertFalse(p.throwAll().isEmpty());
    }
    private void assertReturnsRightCards(Play p, GameCardType t1, GameCardType t2, int n1, int n2)
    {
        int count1 = 0;
        int count2 = 0;
        int countOther = 0;
        for(CardInterface cd : p2.throwAll())
        {
            if(cd.cardType().getName().equals(t1.getName())) count1++;
            else if(cd.cardType().getName().equals(t2.getName())) count2++;
            else countOther++;
        }
        assertEquals(n1,count1);
        assertEquals(n2,count2);
        assertEquals(0,countOther);
    }
    @Test
    public void testPutIntoAndThrowALl()
    {
        setUp();

        assertEmpty(p1);
        p1.putInto(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        assertNotEmpty(p1);

        p2.putInto(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        p2.putInto(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        p2.putInto(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));

        assertReturnsRightCards(p2,GameCardType.GAME_CARD_TYPE_COPPER,GameCardType.GAME_CARD_TYPE_ESTATE,1,2 );
        assertEmpty(p2);

        p2.putInto(new FakeCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        p2.putInto(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));

        assertReturnsRightCards(p2,GameCardType.GAME_CARD_TYPE_PROVINCE,GameCardType.GAME_CARD_TYPE_MARKET,1,1 );

        assertEmpty(p2);
        assertEmpty(p2);
        assertEmpty(p2);
    }
}
