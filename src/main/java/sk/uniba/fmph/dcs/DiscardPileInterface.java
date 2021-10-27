package sk.uniba.fmph.dcs;

import java.util.List;
import java.util.Optional;

public interface DiscardPileInterface
{
     Optional<CardInterface> getTopCard();
     void addCards(List<CardInterface> _cards);
     int getSize();
     List<CardInterface> shuffle();
     int getPoints();
}
