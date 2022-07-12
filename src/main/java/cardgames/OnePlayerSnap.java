package cardgames;

import card.Card;

public class OnePlayerSnap extends Snap {
    @Override
    public void run() {
        setup();
        System.out.println("Press Enter to receive your first card");
        scanner.nextLine();
        Card previousCard;
        Card currentCard = dealCard();
        System.out.println("Your new card is " + currentCard);
        boolean isActive = true;
        while (isActive) {
            System.out.println("Press Enter to receive a new card");
            scanner.nextLine();
            previousCard = currentCard;
            try {
                currentCard = dealCard();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Cards have all been dealt. No one won this round.");
                return;
            }
            System.out.println("Your new card is " + currentCard);
            if (currentCard.getValue() == previousCard.getValue()) {
                if (raceTimer()) {
                    System.out.println("You Win!");
                } else {
                    System.out.println("You Lost!");
                }
                isActive = false;
            }
        }
    }
}
