package sk.uniba.fmph.dcs;

import java.util.ArrayList;

public class Turn
{
    private Hand hand;
    private DiscardPile discardPile;
    private Deck deck;
    private Play play;

    public Turn(TurnStatus ts)
    {
        hand = new Hand();
        discardPile = new DiscardPile(new ArrayList<>());
        deck = new Deck();
        play = new Play(new ArrayList<>());
    }
}
