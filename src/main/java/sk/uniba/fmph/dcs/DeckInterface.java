package sk.uniba.fmph.dcs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface DeckInterface
{
    List<CardInterface> draw(int count);

    int getPoints();
}
