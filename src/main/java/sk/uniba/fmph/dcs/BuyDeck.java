package sk.uniba.fmph.dcs;

import java.util.LinkedList;
import java.util.Optional;

public class BuyDeck implements BuyDeckInterface
{
    private GameCardType cardsType;
    private LinkedList<CardInterface> cards;

    public BuyDeck(GameCardType cardsType, int cardCount)
    {
        this.cardsType = cardsType;

        cards = new LinkedList<>();
        for (int i = 0; i < cardCount; i++)
        {
            cards.add(new GameCard(cardsType));
        }
    }

    @Override
    public Optional<CardInterface> buy()
    {
        if(cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.removeFirst());
    }

    @Override
    public GameCardType getCardsType() {
        return cardsType;
    }

    @Override
    public int getCardsCount() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        return cards.size()==0;
    }
}
