package cardgames;

import players.SnapPlayer;

import java.util.ArrayList;

public class MultiplePlayerSnap extends Snap {
    private final ArrayList<SnapPlayer> players;

    public MultiplePlayerSnap(int numberOfPlayers) {
        super();
        players = makePlayers(numberOfPlayers);
    }

    @Override
    public void run() {
        setup();
        try {
            SnapPlayer winner = getWinner(players.size());
            System.out.println(winner.getName() + " Wins!");
        } catch (IndexOutOfBoundsException e) {//when deck is empty
            System.out.println("Cards have all been dealt. No one won this round.");
        }
    }

    private void displayCards() {
        System.out.println("\nHere are the cards:");
        for (SnapPlayer player: players) {
            System.out.println(player.getName() + ":" + player.getCard());
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
