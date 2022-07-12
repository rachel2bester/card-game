package Initialiser;

import cardgames.CardGame;
import cardgames.MultiplePlayerSnap;
import cardgames.OnePlayerSnap;

public class SnapInitialiser {
    public static void startSnap() {
        System.out.println("Welcome to Snap." +
                "\nHow many players?");
        int numberOfPlayers = CardGame.getIntegerInput(1, 6);
        if (numberOfPlayers == 1) {
            OnePlayerSnap onePlayerSnap = new OnePlayerSnap();
            onePlayerSnap.run();
        } else {
            MultiplePlayerSnap multiplePlayerSnap = new MultiplePlayerSnap(numberOfPlayers);
            multiplePlayerSnap.run();
        }
        if (playAgain()) {
            startSnap();
        }
    }

    private static boolean playAgain() {
        System.out.println("Play Again?" +
                "\nEnter Y/N");
        String input = CardGame.getStringInput();
        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.println("Incorrect Input " +
                    "\nEnter Y/N");
            input = CardGame.getStringInput();
        }
        return input.equalsIgnoreCase("y");
    }
}
