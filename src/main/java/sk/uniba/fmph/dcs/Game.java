package sk.uniba.fmph.dcs;

import java.util.List;

public class Game
{
    private boolean isActionPhase;
    private final Turn turn;
    private final EndGameStrategy endGameStrategy;
    private boolean isGameOver;
    private boolean isWinner;

    public Game(EndGameStrategy endGameStrategy, List<BuyDeckInterface> buyDecks, GameCardFactoryInterface gameCardFactoryInterface) // buyDeck sem asi nepopiera design
    {
        this.endGameStrategy = endGameStrategy;

        isActionPhase = true;
        isGameOver = false;
        isWinner = false;

        TurnStatus ts = new TurnStatus();
        turn = new Turn(ts, buyDecks, gameCardFactoryInterface);
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

        turn.endTurn();
        boolean isGameOver = endGameStrategy.isGameOver();

        endOrWinner.setFirst(isGameOver);
        endOrWinner.setSecond(false);

        if(isGameOver)
        {
            endGame();
            endOrWinner.setSecond(isWinner);
        }
        isActionPhase = true;

        return true;
    }
    private void endGame()
    {
        isGameOver = true;

        int points = turn.getPoints();
        isWinner = points >= 30;
    }
}
