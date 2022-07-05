package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class CardGame {
    private ArrayList<Card> deckOfCards;
    private static String[] suits = {"\u2663", "\u2666", "\u2665", "\u2660"};
    private String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};


    public CardGame() {
        deckOfCards = new ArrayList<>();
        for (int i = 0; i < symbols.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                deckOfCards.add(new Card(suits[j], symbols[i], i + 2));
            }
        }
    }

    public void getDeck() {
        for (Card card: deckOfCards) {
            System.out.println(card);
        }
    }

    public Card dealCard() {
        Card card = deckOfCards.get(0);
        deckOfCards.remove(card);
        return card;
    }

    public void sortDeckIntoSuits() {
        sortDeckInNumberOrder();
        Collections.sort(deckOfCards);
    }

    public void sortDeckInNumberOrder() {
        deckOfCards = deckOfCards.stream()
                .sorted(Comparator.comparing(Card::getValue))
                .collect(toCollection(ArrayList::new));
    }

    public void shuffleDeck() {
        Collections.shuffle(deckOfCards);
    }

    public static String[] getSuits() {
        return suits;
    }




}
