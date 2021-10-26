package sk.uniba.fmph.dcs;

import java.util.List;

public class EndGameStrategyOr implements EndGameStrategy
{
    private final List<EndGameStrategy> endGameStrategies;
    public EndGameStrategyOr(List<EndGameStrategy> endGameStrategies)
    {
        this.endGameStrategies = endGameStrategies;
    }
    @Override
    public boolean isGameOver()
    {
        boolean isGameOver = false;
        for(EndGameStrategy endGameStrategy : endGameStrategies)
        {
            isGameOver = isGameOver || endGameStrategy.isGameOver();
        }
        return isGameOver;
    }
}
