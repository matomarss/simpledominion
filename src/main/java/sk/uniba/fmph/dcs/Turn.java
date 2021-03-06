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
    private List<BuyDeckInterface> buyDecks;
    private TurnStatus turnStatus;

    public Turn(TurnStatus ts, List<BuyDeckInterface> buyDecks, GameCardFactoryInterface gameCardFactoryInterface)
    {
        play = new Play();

        discardPile = new DiscardPile(new ArrayList<>());

        deck = new Deck(discardPile, gameCardFactoryInterface.getInitialCards());
        hand = new Hand(deck);

        this.buyDecks = buyDecks;

        turnStatus = ts;
        resetStatus();
    }

    public boolean playCard(int handIdx)
    {
        if(!hand.isInHand(handIdx)) return false;
        if(hand.isActionCard(handIdx))
        {
            if(turnStatus.actions > 0)
            {
                turnStatus.actions -= 1;
            }
            else return false;
        }

        Optional<CardInterface> playedCard = hand.play(handIdx);
        int toTake = playedCard.get().evaluate(turnStatus);
        play.putInto(playedCard.get());

        hand.draw(toTake);
        return true;
    }

    public boolean buyCard(int buyCardIdx)
    {
        if(buyCardIdx >= buyDecks.size()) return false;
        BuyDeckInterface buyDeck = buyDecks.get(buyCardIdx);
        if(buyDeck.getCardsCount() <= 0) return false;
        if(turnStatus.buys <= 0) return false;
        if(turnStatus.coins < buyDeck.getCardsType().getCost()) return false;

        Optional<CardInterface> toBuy = buyDeck.buy();
        if(toBuy.isPresent())
        {
            turnStatus.buys -= 1;
            turnStatus.coins -= buyDeck.getCardsType().getCost();

            List<CardInterface> toAdd = new ArrayList<>();
            toAdd.add(toBuy.get());
            discardPile.addCards(toAdd);
            return true;
        }
        else return false;
    }
    public void end()
    {
        resetStatus();

        discardPile.addCards(play.throwAll());
        discardPile.addCards(hand.throwAll());

        hand.draw(5);
    }

    private void resetStatus()
    {
        turnStatus.actions = 1;
        turnStatus.buys = 1;
        turnStatus.coins = 0;
    }
    public int getPoints()
    {
        return deck.getPoints() + discardPile.getPoints();
    }

    public List<String> getCardsInHand()
    {
        return hand.getCards();
    }
}