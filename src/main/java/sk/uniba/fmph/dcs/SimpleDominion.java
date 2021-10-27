package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleDominion implements SimpleDominionInterface{
    private Game game;
    public SimpleDominion()
    {
        List<EndGameStrategy> endGameStrategies = new ArrayList<>();

        List<BuyDeckInterface> buyDecks = new ArrayList<>();
        buyDecks.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 10));
        buyDecks.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 10));
        buyDecks.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 5));
        buyDecks.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, 10));
        endGameStrategies.add(new AtLeastNEmptyDecks(buyDecks,3));
        endGameStrategies.add(new EmptyProvinceCardDeck(buyDecks));

        game = new Game(new EndGameStrategyOr(endGameStrategies), buyDecks, new GameCardFactory());
    }
    @Override
    public Optional<GameState> playCard(int handIdx) {
        game.playCard(handIdx);
        return Optional.empty();
    }

    @Override
    public Optional<GameState> endPlayCardPhase() {
        return Optional.empty();
    }

    @Override
    public Optional<GameState> buyCard(int buyCardIdx) {
        return Optional.empty();
    }

    @Override
    public Optional<GameState> endTurn() {
        return Optional.empty();
    }
}
