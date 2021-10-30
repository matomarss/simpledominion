package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class GameCardFactory implements GameCardFactoryInterface{

    private GameCardType type1;
    private GameCardType type2;

    private int n;
    private int m;
    public GameCardFactory(GameCardType type1, int n)
    {
        this.type1 = type1;
        this.n = n;
    }
    public GameCardFactory(GameCardType type1, GameCardType type2, int n,int m)
    {
        this.type1 = type1;
        this.type2 = type2;
        this.n = n;
        this.m=m;
    }
    @Override
    public List<CardInterface> getInitialCards() {
        List<CardInterface> cds = new ArrayList<>();
        List<String> s = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            cds.add(new GameCard(type1));
            s.add(type1.getName());
        }
        for (int i = 0; i < m; i++)
        {
            cds.add(new GameCard(type2));
            s.add(type2.getName());
        }
        return cds;
    }
}
