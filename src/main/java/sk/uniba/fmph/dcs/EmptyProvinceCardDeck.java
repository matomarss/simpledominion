package sk.uniba.fmph.dcs;

import java.util.List;

public class EmptyProvinceCardDeck extends AtLeastNEmptyDecks{

    private List<BuyDeckInterface> buyDecks;

    public EmptyProvinceCardDeck(List<BuyDeckInterface> buyDecks)
    {
       super(buyDecks, 1, "Province");
    }
}
