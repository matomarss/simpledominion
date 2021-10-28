package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameCardFactory7c3eTest
{
    private GameCardFactory7c3e gcf;
    private void setUp()
    {
        gcf = new GameCardFactory7c3e();
    }

    @Test
    public void testGetInitialCards()
    {
        setUp();
        for (int i = 0; i < 10000; i++)
        {
            List<CardInterface> cl = gcf.getInitialCards();
            int estateCount = 0;
            int copperCount = 0;
            int othersCount = 0;
            for (CardInterface cr:cl
            ) {
                if(cr.cardType().getName() == "Estate") estateCount++;
                else if(cr.cardType().getName() == "Copper") copperCount++;
                else othersCount++;
            }
            assertEquals(3, estateCount);
            assertEquals(7, copperCount);
            assertEquals(0,othersCount);
        }
    }
}
