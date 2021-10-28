package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmptyProvinceDeckTest
{
    private EmptyProvinceCardDeck aed1;
    private EmptyProvinceCardDeck aed2;
    private EmptyProvinceCardDeck aed3;
    private EmptyProvinceCardDeck aed4;
    private EmptyProvinceCardDeck aed5;
    private ArrayList<BuyDeckInterface> bds1;
    private ArrayList<BuyDeckInterface> bds2;
    private ArrayList<BuyDeckInterface> bds3;
    private ArrayList<BuyDeckInterface> bds4;
    private ArrayList<BuyDeckInterface> bds5;

    private void setUp()
    {
        bds1 = new ArrayList<>();
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 5));
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds1.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        aed1 = new EmptyProvinceCardDeck(bds1);

        bds2 = new ArrayList<>();
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 0));
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        aed2 = new EmptyProvinceCardDeck(bds2);

        bds3 = new ArrayList<>();
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 0));
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 0));
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds3.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        aed3 = new EmptyProvinceCardDeck(bds3);

        bds4 = new ArrayList<>();
        bds4.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 0));
        bds4.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 0));
        bds4.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds4.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        aed4 = new EmptyProvinceCardDeck(bds4);

        bds5 = new ArrayList<>();
        bds5.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds5.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds5.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        bds5.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 5));
        aed5 = new EmptyProvinceCardDeck(bds5);
    }

    @Test
    public void TestIsGameOver()
    {
        setUp();
        assertFalse(aed1.isGameOver());
        assertTrue(aed2.isGameOver());
        assertTrue(aed3.isGameOver());
        assertFalse(aed4.isGameOver());
        assertFalse(aed5.isGameOver());
    }
}
