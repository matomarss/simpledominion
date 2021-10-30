package sk.uniba.fmph.dcs;

public class GameCard implements CardInterface{

    private GameCardTypeInterface cardType;
    public GameCard(GameCardTypeInterface cardType)
    {
        this.cardType = cardType;
    }
    @Override
    public int evaluate(TurnStatus ts)
    {
        ts.actions += cardType.getPlusActions();
        ts.buys += cardType.getPlusBuys();
        ts.coins += cardType.getPlusCoins();

        return cardType.getPlusCards();
    }

    @Override
    public GameCardTypeInterface cardType()
    {
        return cardType;
    }

    @Override
    public String toString() {
        return cardType.getName();
    }
}
