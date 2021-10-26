package sk.uniba.fmph.dcs;

import java.util.Optional;

public class BuyDeck
{
    private int cardCount;

    public Optional<CardInterface> buy()
    {
        return null;
    }

    public static CardInterface createCard(String name)
    {
        switch (name)
        {
            case "copper":
                return new GameCard(GameCardType.GAME_CARD_TYPE_COPPER);
            case "estate":
                return new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE);
            case "festival":
                return new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL);
            case "market":
                return new GameCard(GameCardType.GAME_CARD_TYPE_MARKET);
            case "smithy":
                return new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY);
            case "laboratory":
                return new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY);
            case "village":
                return new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE);
            default: return null;
        }
    }
}
