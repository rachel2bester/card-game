package cardgames;

import card.Card;
import players.SnapPlayer;

import java.util.ArrayList;
import java.util.Timer;

public class Snap extends CardGame {
    ArrayList<SnapPlayer> players;

    @Override
    public void run() {
        shuffleDeck();
        System.out.println("Welcome to Snap." +
                "\nHow many players?");
        int numberOfPlayers = getIntegerInput(1, 6);
        if (numberOfPlayers == 1) {
            onePlayerGame();
        } else {
            multiplePlayerGame(numberOfPlayers);
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
                currentPlayer.setCard(dealCard());
                System.out.println("Your card is: " + currentPlayer.getCard());
                displayCards();
                if (hasWon(currentPlayer)) {
                    isActive = false;
                    break;
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
            currentCard = dealCard();
            System.out.println("Your new card is " + currentCard);
            if (currentCard.getValue() == previousCard.getValue()) {


                System.out.println("You Win");
                isActive = false;
            }

        }


    }

    private boolean hasWon(SnapPlayer currentPlayer) {
        for (SnapPlayer player: players) {
            if (player != currentPlayer && player.getCard() != null && player.getCard().getValue() == currentPlayer.getCard().getValue()) {
                System.out.println("\n" + currentPlayer.getName() + " Wins!");
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
