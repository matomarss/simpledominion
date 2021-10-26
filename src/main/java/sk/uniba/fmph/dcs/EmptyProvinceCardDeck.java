package sk.uniba.fmph.dcs;

import java.util.List;

public class EmptyProvinceCardDeck implements EndGameStrategy{
    private List<BuyDeckInterface> buyDecks;
    public EmptyProvinceCardDeck(List<BuyDeckInterface> buyDecks)
    {
        this.buyDecks = buyDecks;
    }
    @Override
    public boolean isGameOver()
    {
        for(BuyDeckInterface buyDeck : buyDecks)
        {
            if(buyDeck.getCardsType().getName().equals("Province") && buyDeck.isEmpty()) return true;
        }
        return false;
    }
}
