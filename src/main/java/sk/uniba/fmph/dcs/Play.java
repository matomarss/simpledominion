package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Play
{
    private List<CardInterface> cards;
    public Play()
    {
        cards = new ArrayList<>();
    }

    public void putInto(CardInterface card) {
        cards.add(card);
    }

    public List<CardInterface> throwAll()
    {
        List<CardInterface> toReturn = cards;
        cards = new ArrayList<>();
        return toReturn;
    }
}
