package sk.uniba.fmph.dcs;

public class Game
{
    private boolean isActionPhase;
    private Turn turn;
    private EndGameStrategy endGameStrategy;

    public Game(EndGameStrategy endGameStrategy)
    {
        this.endGameStrategy = endGameStrategy;

        isActionPhase = true;
        TurnStatus ts = new TurnStatus();
        ts.actions = 1;
        ts.buys = 1;
        ts.coins = 0;
        turn = new Turn(ts);
    }
    public boolean playCard(int handIdx)
    {
        if(!isActionPhase) return false;


        return true;
    }
    public boolean endPlayCardPhase()
    {
        return true;
    }
    public boolean buyCard(int buyCardIdx)
    {
        return true;
    }
    public boolean endTurn()
    {
        return true;
    }
}
