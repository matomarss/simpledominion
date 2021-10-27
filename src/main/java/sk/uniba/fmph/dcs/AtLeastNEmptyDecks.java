package sk.uniba.fmph.dcs;

import java.util.List;

public class AtLeastNEmptyDecks implements EndGameStrategy{
    private List<BuyDeckInterface> buyDecks;
    private int n;
    public AtLeastNEmptyDecks(List<BuyDeckInterface> buyDecks, int n)
    {
        this.buyDecks = buyDecks;
        this.n = Math.abs(n);
    }
    @Override
    public boolean isGameOver()
    {
        int emptyCount = 0;
        for(BuyDeckInterface buyDeck : buyDecks)
        {
            if(buyDeck.isEmpty()) emptyCount++;
        }
        return emptyCount >= n;
    }
}
