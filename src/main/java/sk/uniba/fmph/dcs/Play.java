package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Play
{
    List<CardInterface> cards;
    public Play(List<CardInterface> _cards)
    {
        cards = _cards;
    }
    public void addCards(List<CardInterface> _cards) {
        cards.addAll(_cards);
    }

    public List<CardInterface> throwAll()
    {
        List<CardInterface> toReturn = cards;
        cards = new ArrayList<>();
        return toReturn;
    }
}
