package cardgames;

import card.Card;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public abstract class CardGame {
    private ArrayList<Card> deckOfCards;
    private final static String[] suits = {"\u2663", "\u2666", "\u2665", "\u2660"};
    protected final Scanner scanner = new Scanner(System.in);


    public CardGame() {
        deckOfCards = new ArrayList<>();
        String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (int i = 0; i < symbols.length; i++) {
            for (String suit: suits) {
                deckOfCards.add(new Card(suit, symbols[i], i + 2));
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

    public abstract void run();

    public String getStringInput() {
        System.out.println("Enter text");
        String userInput = scanner.nextLine();

        if (userInput.length() > 0) {
            return userInput;
        } else {
            System.out.println("Unable to understand input, try again");
            return getStringInput();
        }
    }

    public int getIntegerInput(int min, int max) {
        System.out.println("Enter a number between "+ min +" and " + max);
        int userInput;


        try {
             userInput = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter an integer");
            scanner.nextLine();
            return getIntegerInput(min, max);
        }

        if (userInput >= min && userInput <= max) {
            scanner.nextLine();
            return userInput;
        } else {
            System.out.println("Incorrect Input");
            return getIntegerInput(min, max);
        }

    }
}
