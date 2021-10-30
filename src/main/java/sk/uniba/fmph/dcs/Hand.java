package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        myCards.addAll(myDeck.draw(Math.abs(count)));
    }

    public boolean isInHand(int idx)
    {
        return idx < myCards.size() && idx >= 0;
    }

    public boolean isActionCard(int idx)
    {
        if(!isInHand(idx)) return false;

        return myCards.get(idx).cardType().isAction();
    }

    public Optional<CardInterface> play(int idx)
    {
        if(!isInHand(idx)) return Optional.empty();

        return Optional.of(myCards.remove(idx));
    }

    public List<CardInterface> throwAll()
    {
        List<CardInterface> toReturn = myCards;
        myCards = new ArrayList<>();
        return toReturn;
    }

    public List<String> getCards()
    {
        List<String> toReturn = new ArrayList<>();
        int i = 0;
        while(isInHand(i))
        {
            toReturn.add(myCards.get(i).cardType().getName());
            i++;
        }
        return toReturn;
    }
}
