package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Hand
{
    private DeckInterface myDeck;
    private List<CardInterface> myCards;

    public Hand(DeckInterface herDeck)
    {
        myDeck = herDeck;
        myCards = new ArrayList<>();
        draw(5);
    }

    public void draw(int count)
    {
        myCards.addAll(myDeck.draw(count));
    }

    public boolean isInHand(int idx)
    {
        return idx < myCards.size() && idx >= 0;
    }

    public boolean isActionCard(int idx)
    {
        return myCards.get(idx).cardType().isAction();
    }

    public CardInterface play(int idx)
    {
        return myCards.remove(idx);
    }

    public List<CardInterface> throwAll()
    {
        List<CardInterface> toReturn = myCards;
        myCards = new ArrayList<>();
        return toReturn;
    }
}
