package card;

import cardgames.CardGame;

public class Card implements Comparable<Card>{
    String suit;
    String symbol;
    int value;

    public Card(String suit, String symbol, int value) {
        this.suit = suit;
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return symbol + suit;
    }
    public String getSuit() {
        return suit;
    }
    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        return this.getSuitValue() - o.getSuitValue();
    }

    private int getSuitValue() {
        String[] suits = CardGame.getSuits();
        for (int i = 0; i < suits.length; i++) {
            if (suit.equals(suits[i])) {
                return i;
            }
        }
        return -1;
    }
}
