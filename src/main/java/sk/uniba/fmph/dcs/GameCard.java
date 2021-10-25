package sk.uniba.fmph.dcs;

public class GameCard implements CardInterface{

    private GameCardType cardType;
    public GameCard(GameCardType cardType)
    {
        this.cardType = cardType;
    }
    @Override
    public int evaluate(TurnStatus ts) // zmenil som navratovu hodnotu
    {
        ts.actions += cardType.plusActions;
        ts.buys += cardType.plusBuys;
        ts.coins += cardType.plusCoins;

        return cardType.plusCards;
    }

    @Override
    public GameCardType cardType()
    {
        return cardType;
    }
}
