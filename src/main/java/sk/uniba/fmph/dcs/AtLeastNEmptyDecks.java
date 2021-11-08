package sk.uniba.fmph.dcs;

import java.util.List;

public class AtLeastNEmptyDecks implements EndGameStrategy{
    private List<BuyDeckInterface> buyDecks;
    private int n;
    private String cardTypeName = null;
    public AtLeastNEmptyDecks(List<BuyDeckInterface> buyDecks, int n)
    {
        this.buyDecks = buyDecks;
        this.n = Math.abs(n);
    }

    public AtLeastNEmptyDecks(List<BuyDeckInterface> buyDecks, int n, String cardType)
    {
        this.buyDecks = buyDecks;
        this.n = Math.abs(n);
        cardTypeName = cardType;
    }

    @Override
    public boolean isGameOver()
    {
        int emptyCount = 0;
        for(BuyDeckInterface buyDeck : buyDecks)
        {
            if(cardTypeName != null && !buyDeck.getCardsType().getName().equals(cardTypeName)) continue;

            if(buyDeck.isEmpty()) emptyCount++;
        }
        return emptyCount >= n;
    }
}
