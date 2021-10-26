package sk.uniba.fmph.dcs;

import java.util.List;

public class Game
{
    private boolean isActionPhase;
    private Turn turn;
    private EndGameStrategy endGameStrategy;

    public Game(EndGameStrategy endGameStrategy, List<BuyDeckInterface> buyDecks) // buyDeck sem asi nepopiera design
    {
        this.endGameStrategy = endGameStrategy;

        isActionPhase = true;
        TurnStatus ts = new TurnStatus();
        ts.actions = 1;
        ts.buys = 1;
        ts.coins = 0;
        turn = new Turn(ts, buyDecks);
    }
    public boolean playCard(int handIdx)
    {
        if(!isActionPhase) return false;

        return turn.playCard(handIdx);
    }
    public boolean endPlayCardPhase()
    {
        if(!isActionPhase) return false;

        isActionPhase = false;
        return true;
    }
    public boolean buyCard(int buyCardIdx)
    {
        if(isActionPhase) return false;

        return turn.buyCard(buyCardIdx);
    }
    public boolean endTurn()
    {
        return true;
    }
}
