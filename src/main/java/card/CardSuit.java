package card;

public enum CardSuit {
    Clubs ("\u2663", 0),
    Diamonds ("\u2666", 1),
    Hearts ("\u2665", 2),
    Spades ("\u2660", 3);
    private final String icon;
    private final int value;

    CardSuit(String icon, int value) {
        this.icon = icon;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return icon;
    }
}
