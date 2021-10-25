package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Deck
{
    List<CardInterface> cards;
    public Deck(List<CardInterface> _cards)
    {
        cards = _cards;
    }

    public List<CardInterface> draw(int count)
    {
        List<CardInterface> toReturn = new ArrayList<>();
        for(int i = cards.size()-1; i > cards.size()-1-count;i--)
        {
            toReturn.add(cards.get(i));
            cards.remove(cards.get(i));
        }
        return toReturn;
    }

    //should shuffle
}
