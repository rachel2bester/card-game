package players;

import card.Card;

public class SnapPlayer extends Player{

    private Card card;

    public SnapPlayer(String name) {
        super(name);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
