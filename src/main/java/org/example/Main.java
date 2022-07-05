package org.example;

public class Main {
    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.shuffleDeck();
        cardGame.getDeck();
        cardGame.sortDeckIntoSuits();
        cardGame.getDeck();
    }
}