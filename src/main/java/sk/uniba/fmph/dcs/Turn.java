package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Turn
{
    private Hand hand;
    private DiscardPile discardPile;
    private Deck deck;
    private Play play;
    private List<BuyDeck> buyDecks;
    private TurnStatus turnStatus;

    public Turn(TurnStatus ts, List<BuyDeck> buyDecks)
    {
        play = new Play();

        discardPile = new DiscardPile(new ArrayList<>());

        deck = new Deck(discardPile);

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
