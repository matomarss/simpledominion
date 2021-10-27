package sk.uniba.fmph.dcs;

import java.util.LinkedList;
import java.util.List;

public class GameCardFactory implements GameCardFactoryInterface
{
    @Override
    public List<CardInterface> getInitialCards()
    {
        List<CardInterface> cards = new LinkedList<>();
        for(int i = 0; i < 7; i++)
        {
            cards.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }
        for(int i = 0; i < 3; i++)
        {
            cards.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }

        return cards;
    }
}
