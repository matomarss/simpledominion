package sk.uniba.fmph.dcs;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BuyDeck implements BuyDeckInterface
{
    private int cardCount;
    private GameCardType cardsType;
    private LinkedList<CardInterface> cards;

    public BuyDeck(GameCardType cardsType, int cardCount)
    {
        this.cardsType = cardsType;
        this.cardCount = cardCount;

        cards = new LinkedList<>();
        for (int i = 0; i < cardCount; i++)
        {
            cards.add(new GameCard(cardsType));
        }
    }
    public Optional<CardInterface> buy()
    {
        if(cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.removeFirst());
    }
}
