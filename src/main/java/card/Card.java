package card;

public class Card implements Comparable<Card>{
    private final CardSuit suit;
    private final String symbol;
    private final int value;

    public Card(CardSuit suit, String symbol, int value) {
        this.suit = suit;
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return symbol + suit;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        return this.suit.getValue() - o.suit.getValue();
    }
}
