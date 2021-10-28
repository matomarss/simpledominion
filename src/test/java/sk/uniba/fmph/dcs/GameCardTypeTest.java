package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GameCardTypeTest
{
    private GameCardType gct1;
    private GameCardType gct2;
    private void setUp()
    {
        gct1 = new GameCardType(1,2,3,4,5,6,true,"prva","d1");
        gct2 = new GameCardType(6,5,4,3,2,1,false,"druha","d2");
    }

    @Test
    public void testGet()
    {
        setUp();
        assertEquals(1,gct1.getPlusActions());
        assertEquals(6,gct2.getPlusActions());

        assertEquals(2,gct1.getPlusBuys());
        assertEquals(5,gct2.getPlusBuys());

        assertEquals(3,gct1.getPlusCards());
        assertEquals(4,gct2.getPlusCards());

        assertEquals(4,gct1.getPlusCoins());
        assertEquals(3,gct2.getPlusCoins());

        assertEquals(5,gct1.getPoints());
        assertEquals(2,gct2.getPoints());

        assertEquals(6,gct1.getCost());
        assertEquals(1,gct2.getCost());

        assertEquals("prva",gct1.getName());
        assertEquals("druha",gct2.getName());

        assertEquals("d1",gct1.getDescription());
        assertEquals("d2",gct2.getDescription());
    }
    @Test
    public void testIsAction()
    {
        setUp();
        assertTrue(gct1.isAction());
        assertFalse(gct2.isAction());
    }
}
