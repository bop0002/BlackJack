package blackjack.Core;

import blackjack.Object.*;

public class UIManager {

    public static void showStatus(Player player, Dealer dealer, Deck deck) {
        Terminal.clear();
        System.out.println("== Dealer ==");
        CardPrinter.printCards(dealer.getCard(), null);
        System.out.println("Dealer: " + dealer.getPoint());
        System.out.println("----------------------------");
        System.out.println("== Player ==");
        CardPrinter.printCards(player.getCard(), null);
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
