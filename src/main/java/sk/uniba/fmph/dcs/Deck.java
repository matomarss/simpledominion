package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck
{
    private List<CardInterface> cards;
    private DiscardPile discardPile;
    public Deck(DiscardPile discardPile, List<CardInterface> cards)
    {
        this.cards = cards;
        shuffle();

        this.discardPile = discardPile;
    }

    private void shuffle()
    {
        Collections.shuffle(cards);
    }

    public List<CardInterface> draw(int count)
    {
        List<CardInterface> toReturn = new LinkedList<>();
        if(count > cards.size())
        {
            cards.addAll(0, discardPile.shuffle());
        }
        if(count > cards.size())
        {
            count = cards.size();
        }
        for(int i = cards.size()-1; i > cards.size()-1-count;i--)
        {
            toReturn.add(cards.get(i));
            cards.remove(cards.get(i));
        }
        return toReturn;
    }

    //should shuffle
}
