package sk.uniba.fmph.dcs;

public interface GameCardTypeInterface
{
    int getPlusActions();
    int getPlusBuys();
    int getPlusCards();
    int getPlusCoins();
    int getPoints();
    int getCost();
    boolean isAction();
    String getName();
    String getDescription();
}
