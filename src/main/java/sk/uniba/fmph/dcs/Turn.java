package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Turn
{
    private Hand hand;
    private DiscardPile discardPile;
    private Deck deck;
    private Play play;
    private List<BuyDeckInterface> buyDecks;
    private TurnStatus turnStatus;

    public Turn(TurnStatus ts, List<BuyDeckInterface> buyDecks)
    {
        play = new Play();

        discardPile = new DiscardPile(new ArrayList<>());

        List<CardInterface> cards = new LinkedList<>();
        for(int i = 0; i < 7; i++)
        {
            cards.add(buyDecks.get(1).buy().get());
        }
        for(int i = 0; i < 3; i++)
        {
            cards.add(buyDecks.get(0).buy().get());
        }

        deck = new Deck(discardPile, cards);

        hand = new Hand(deck);

        this.buyDecks = buyDecks;

        turnStatus = ts;
    }

    public boolean playCard(int handIdx)
    {
        if(turnStatus.actions > 0 && hand.isInHand(handIdx) && hand.isActionCard(handIdx))
        {
            turnStatus.actions -= 1;
            Optional<CardInterface> playedCard = hand.play(handIdx);
            int toTake = playedCard.get().evaluate(turnStatus);
            play.throwCard(playedCard.get());

            hand.draw(toTake);
            return true;
        }

        return false;
    }
}
