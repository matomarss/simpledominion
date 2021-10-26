package sk.uniba.fmph.dcs;

import java.util.List;

public class Game
{
    private boolean isActionPhase;
    private final Turn turn;
    private final EndGameStrategy endGameStrategy;
    private boolean isGameOver;

    public Game(EndGameStrategy endGameStrategy, List<BuyDeckInterface> buyDecks) // buyDeck sem asi nepopiera design
    {
        this.endGameStrategy = endGameStrategy;

        isActionPhase = true;
        isGameOver = false;
        TurnStatus ts = new TurnStatus();
        turn = new Turn(ts, buyDecks);
    }
    public boolean playCard(int handIdx)
    {
        if(isGameOver) return false;

        if(!isActionPhase) return false;

        return turn.playCard(handIdx);
    }
    public boolean endPlayCardPhase()
    {
        if(isGameOver) return false;

        if(!isActionPhase) return false;

        isActionPhase = false;
        return true;
    }
    public boolean buyCard(int buyCardIdx)
    {
        if(isGameOver) return false;

        if(isActionPhase) return false;

        return turn.buyCard(buyCardIdx);
    }
    public boolean endTurn(Couple<Boolean> endOrWinner)
    {
        if(isGameOver) return false;

        endOrWinner.setFirst(endGameStrategy.isGameOver());
        endOrWinner.setSecond(false);
        if(isGameOver)
        {
            endOrWinner.setSecond(endGame());
        }
        isActionPhase = true;

        return true;
    }
    private boolean endGame()
    {
        int points = turn.getPoints();
        isGameOver = true;
        return points >= 30;
    }
}
