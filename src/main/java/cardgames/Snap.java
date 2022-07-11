package cardgames;

import card.Card;
import players.SnapPlayer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snap extends CardGame {
    private ArrayList<SnapPlayer> players;
    private boolean gameOver;

    @Override
    public void run() {
        gameOver = false;
        makeNewDeck();
        shuffleDeck();
        sortDeckIntoSuits();
        System.out.println("Welcome to Snap." +
                "\nHow many players?");
        int numberOfPlayers = getIntegerInput(1, 6);
        if (numberOfPlayers == 1) {
            onePlayerGame();
        } else {
            multiplePlayerGame(numberOfPlayers);
        }
        System.out.println("Play Again?" +
                "\nEnter Y/N");
        String input = getStringInput();
        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.println("Incorrect Input " +
                    "\nEnter Y/N");
            input = getStringInput();
        }
        if (input.equalsIgnoreCase("y")) {
            run();
        }
    }

    private void displayCards() {
        System.out.println("\nHere are the cards:");
        for (SnapPlayer player: players) {
            System.out.println(player.getName() + ":" + player.getCard());
        }
    }

    private void multiplePlayerGame(int numberOfPlayers) {
        players = makePlayers(numberOfPlayers);
        SnapPlayer currentPlayer;
        boolean isActive = true;

        while (isActive) {
            for (int i = 0; i < numberOfPlayers; i++) {
                currentPlayer = players.get(i);
                System.out.println("\nIt is " + currentPlayer.getName() + "'s turn" +
                        "\nPress Enter to receive your card");
                scanner.nextLine();
                try {
                    currentPlayer.setCard(dealCard());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Cards have all been dealt. No one won this round.");
                    return;
                }
                System.out.println("Your card is: " + currentPlayer.getCard());
                displayCards();
                if (hasMatch(currentPlayer)) {
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("\nTime's up" +
                                    "\nThe game continues.");
                            gameOver = false;
                        }
                    };

                    Timer timer = new Timer();
                    String input = "";
                    timer.schedule( task, 2 * 1000);
                    gameOver = true;
                    while (!input.equalsIgnoreCase("snap") && gameOver) {
                        input = scanner.nextLine();
                    }
                    timer.cancel();
                    if (input.equals("snap") && gameOver) {
                        System.out.println("You Win!");
                        isActive = false;
                        break;
                    }
                }
            }
        }
    }

    private void onePlayerGame() {
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
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("\nTime's up" +
                                "\nYou lost!");
                        gameOver = true;
                    }
                };
                Timer timer = new Timer();
                String input = "";
                timer.schedule( task, 2 * 1000);
                while (!input.equals("snap") && !gameOver) {
                    input = scanner.nextLine();
                }
                timer.cancel();
                if (input.equals("snap") && !gameOver) {
                    System.out.println("You Win!");
                    gameOver = true;
                    scanner.nextLine();
                }
                isActive = false;
            }
        }
    }

    private boolean hasMatch(SnapPlayer currentPlayer) {
        for (SnapPlayer player: players) {
            if (player != currentPlayer && player.getCard() != null && player.getCard().getValue() == currentPlayer.getCard().getValue()) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<SnapPlayer> makePlayers(int numberOfPlayers) {
        ArrayList<SnapPlayer> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter name for Player "+ (i + 1));
            String playerName = getStringInput();
            players.add(new SnapPlayer(playerName));
        }
        return players;
    }

}
