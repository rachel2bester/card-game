package cardgames;

import card.Card;
import players.SnapPlayer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snap extends CardGame {
    private ArrayList<SnapPlayer> players;
    private boolean timesUp;

    @Override
    public void run() {
        makeNewDeck();
        //shuffleDeck();
        sortDeckIntoSuits();
        //sortDeckInNumberOrder();
        System.out.println("Welcome to Snap." +
                "\nHow many players?");
        int numberOfPlayers = getIntegerInput(1, 6);
        if (numberOfPlayers == 1) {
            onePlayerGame();
            OnePlayerSnap onePlayerSnap = new OnePlayerSnap();
            onePlayerSnap.run();
        } else {
            multiplePlayerGame(numberOfPlayers);
        }
        if (playAgain()) {
            run();
        }
    }

    public void setup() {
        makeNewDeck();
        shuffleDeck();
    }

    private boolean playAgain() {
        System.out.println("Play Again?" +
                "\nEnter Y/N");
        String input = getStringInput();
        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.println("Incorrect Input " +
                    "\nEnter Y/N");
            input = getStringInput();
        }
        if (input.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
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
        try {
            SnapPlayer winner = getWinner(numberOfPlayers);
            System.out.println(winner.getName() + " Wins!");
        } catch (IndexOutOfBoundsException e) {//when deck is empty
            System.out.println("Cards have all been dealt. No one won this round.");
        }
    }

    private SnapPlayer getWinner(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            SnapPlayer currentPlayer = players.get(i);
            boolean win = turn(currentPlayer);
            if (win) {
                return currentPlayer;
            }
        }
        return getWinner(numberOfPlayers);//new round
    }

    private boolean turn(SnapPlayer currentPlayer) {//return if win
        System.out.println("\nIt is " + currentPlayer.getName() + "'s turn" +
                "\nPress Enter to receive your card");
        scanner.nextLine();
        currentPlayer.setCard(dealCard());
        System.out.println("Your card is: " + currentPlayer.getCard());
        displayCards();
        if (hasMatch(currentPlayer)) {
            if (raceTimer()) {
                return true;
            } else {
                System.out.println("The game continues");
            }
        }
        return false;
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
                if (raceTimer()) {
                    System.out.println("You Win!");
                } else {
                    System.out.println("You Lost!");
                }
                isActive = false;
            }
        }
    }

    private boolean raceTimer() {//returns if win or not
        timesUp = false;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up");
                timesUp = true;
            }
        };
        Timer timer = new Timer();
        String input = "";
        timer.schedule(task, 2 * 1000);
        while (!input.equals("snap") && !timesUp) {
            input = scanner.nextLine();
        }
        timer.cancel();
        if (input.equals("snap") && !timesUp) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasMatch(SnapPlayer currentPlayer) {
        for (SnapPlayer player: players) {
            if (player != currentPlayer
                    && player.getCard() != null
                    && player.getCard().getValue() == currentPlayer.getCard().getValue()) {
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
