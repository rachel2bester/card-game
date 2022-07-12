package cardgames;

import card.Card;

public class OnePlayerSnap extends Snap {
    @Override
    public void run() {
        setup();
        System.out.println("Press Enter to receive your first card");
        scanner.nextLine();
        Card currentCard = dealCard();
        System.out.println("Your new card is " + currentCard);
        try {
            boolean hasWon = round(currentCard);
            if (hasWon) {
                System.out.println("You Win!");
            } else {
                System.out.println("You Lost!");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Cards have all been dealt. No one won this game.");
        }
    }

    private boolean round(Card previousCard) {
        System.out.println("Press Enter to receive a new card");
        scanner.nextLine();
        Card currentCard = dealCard();
        System.out.println("Your new card is " + currentCard);

        if (currentCard.getValue() == previousCard.getValue()) {
            return raceTimer();
        } else {
            return round(currentCard);
        }
    }
}
