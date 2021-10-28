package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class StubEndGameStrategy implements EndGameStrategy
{
    private boolean toReturn;
    public StubEndGameStrategy(boolean toReturn)
    {
        this.toReturn = toReturn;
    }
    @Override
    public boolean isGameOver()
    {
        return toReturn;
    }
}
public class EndGameStrategyOrTest
{
    private EndGameStrategyOr o1;
    private EndGameStrategyOr o2;
    private EndGameStrategyOr o3;
    private EndGameStrategyOr o4;
    private ArrayList<EndGameStrategy> esl1;
    private ArrayList<EndGameStrategy> esl2;
    private ArrayList<EndGameStrategy> esl3;
    private ArrayList<EndGameStrategy> esl4;

    private void setUp()
    {
        esl1 = new ArrayList<>();
        esl1.add(new StubEndGameStrategy(true));
        esl1.add(new StubEndGameStrategy(true));
        o1 = new EndGameStrategyOr(esl1);

        esl2 = new ArrayList<>();
        esl2.add(new StubEndGameStrategy(false));
        esl2.add(new StubEndGameStrategy(false));
        o2 = new EndGameStrategyOr(esl2);

        esl3 = new ArrayList<>();
        esl3.add(new StubEndGameStrategy(false));
        esl3.add(new StubEndGameStrategy(true));
        o3 = new EndGameStrategyOr(esl3);

        esl4 = new ArrayList<>();
        esl4.add(new StubEndGameStrategy(false));
        esl4.add(new StubEndGameStrategy(false));
        esl4.add(new StubEndGameStrategy(true));
        esl4.add(new StubEndGameStrategy(false));
        o4 = new EndGameStrategyOr(esl4);
    }

    @Test
    public void TestIsGameOver()
    {
        setUp();
        assertTrue(o1.isGameOver());
        assertFalse(o2.isGameOver());
        assertTrue(o3.isGameOver());
        assertTrue(o4.isGameOver());
    }
}
