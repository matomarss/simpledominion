package sk.uniba.fmph.dcs;

import java.util.Optional;

public interface BuyDeckInterface
{
    Optional<CardInterface> buy();
    GameCardType getCardsType();
    int getCardsCount();
    boolean isEmpty();
}
