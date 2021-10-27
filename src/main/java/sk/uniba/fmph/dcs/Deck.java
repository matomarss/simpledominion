package sk.uniba.fmph.dcs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck
{
    private LinkedList<CardInterface> cards;
    private DiscardPile discardPile;
    public Deck(DiscardPile discardPile, List<CardInterface> cards)
    {
        this.cards = new LinkedList<>(cards);
        shuffle();

        this.discardPile = discardPile;
    }

    private void shuffle()
    {
        Collections.shuffle(cards);
    }

    public List<CardInterface> draw(int count)
    {
        LinkedList<CardInterface> toReturn = new LinkedList<>();
        if(count > cards.size())
        {
            cards.addAll(0, discardPile.shuffle());
        }
        if(count > cards.size())
        {
            count = cards.size();
        }

        for(int i = 0; i < count;i++)
        {
            toReturn.add(cards.removeLast());
        }
        return toReturn;
    }

    public int getPoints()
    {
        int points = 0;
        for(CardInterface card :cards)
        {
            points += card.cardType().getPoints();
        }
        return points;
    }
}
