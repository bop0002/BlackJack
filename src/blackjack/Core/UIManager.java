package blackjack.Core;

import blackjack.Object.*;

public class UIManager {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";

    public static String getRed() {
        return RED;
    }

    public static String getCyan() {
        return CYAN;
    }

    public static String getGreen() {
        return GREEN;
    }

    public static String getYellow() {
        return YELLOW;
    }

    public static String getReset() {
        return RESET;
    }

    public static void showStatus(Player player, Dealer dealer, Deck deck) {
        Terminal.clear();
        System.out.println("== Dealer ==");
        dealer.showAllHand();
        System.out.println("Dealer: " + dealer.getPoint());
        System.out.println("----------------------------");
        System.out.println("== Player ==");
        player.showAllHand();
        System.out.println("Player: " + player.getPoint());
        CardPrinter.printDeck(deck);
        System.out.println();
    }

    public static void dealerWin() {
        System.out.println("You Lose");
    }

    public static void playerWin() {
        System.out.println("You Win");
    }

    public static void showAction() {
        System.out.println("1. Hit");
        System.out.println("2. Stand");
    }

    public static void tie() {
        System.out.println("Tie");
    }

}
