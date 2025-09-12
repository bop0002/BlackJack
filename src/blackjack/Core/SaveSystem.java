package blackjack.Core;

import java.util.*;
import blackjack.Object.Card;
import blackjack.Object.Deck;
import Enum.Rank;
import Enum.Suit;

public class SaveSystem {



    private static String colorSuit(String suit) {
        if ("♦♥".contains(suit)) {
            return UIManager.getRed() + suit + UIManager.getReset();
        } else {
            return UIManager.getGreen() + suit + UIManager.getReset();
        }
    }

    public static void printCards(ArrayList<Card> cards, boolean hidden[]) {
        if (cards.isEmpty()) {
            return;
        }
        if (hidden == null) {
            hidden = new boolean[cards.size()];
        }
        ArrayList<String[]> cardArt = new ArrayList<>(cards.size());

        for (int i = 0; i < cards.size(); i++) {
            cardArt.add(cardToLines(cards.get(i), hidden[i]));
        }

        final int H = cardArt.get(0).length;
        for (int r = 0; r < H; r++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cardArt.size(); i++) {
                if (i > 0) {
                    sb.append("  ");
                }
                sb.append(cardArt.get(i)[r]);
            }
            System.out.println(sb.toString());
        }

    }

    public static void printCardsHideFirst(ArrayList<Card> cards, boolean hideFirst) {
        boolean[] hidden = new boolean[cards.size()];
        if (hideFirst == true && !cards.isEmpty()) {
            hidden[0] = true;
        }
        printCards(cards, hidden);
    }

    private static String[] cardToLines(Card card, boolean hidden) {
        String topL = "╔";
        String topR = "╗";
        String botL = "╚";
        String botR = "╝";
        String hor = "═";
        String ver = "║";

        String rankStr = hidden ? "?" : card.getRank().getArt();
        String suitStr = hidden ? "?" : colorSuit(card.getSuit().getArt());

        String line0 = topL + repeat(hor, 9) + topR;
        String line1 = ver + padRight(rankStr, 2) + repeat(" ", 7) + ver;
        String line2 = ver + repeat(" ", 9) + ver;
        String line3 = ver + center(suitStr, 9) + ver;
        String line4 = ver + repeat(" ", 9) + ver;
        String line5 = ver + repeat(" ", 7) + padLeft(rankStr, 2) + ver;
        String line6 = botL + repeat(hor, 9) + botR;

        return new String[]{line0, line1, line2, line3, line4, line5, line6};
    }

    public static void printDeck(Deck deck) {
        String top = UIManager.getYellow() + "╔═══════════════╗" + UIManager.getReset();
        String middle = UIManager.getYellow() + String.format("║  DECK: %-4d   ║", deck.getSize()) + UIManager.getReset();
        String bottom = UIManager.getYellow() + "╚═══════════════╝" + UIManager.getReset();

        System.out.println(top);
        System.out.println(middle);
        System.out.println(bottom);
    }

    private static String repeat(String ch, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private static String padRight(String s, int w) {
        if (s == null) {
            s = "";
        }
        return String.format("%-" + w + "s", s);
    }

    private static String padLeft(String s, int w) {
        if (s == null) {
            s = "";
        }
        return String.format("%" + w + "s", s);
    }

    private static String center(String s, int w) {
        if (s == null) {
            s = "";
        }
        int len = s.replaceAll("\u001B\\[[;\\d]*m", "").length();
        if (len >= w) {
            return s;
        }
        int left = (w - len) / 2;
        int right = w - len - left;
        return repeat(" ", left) + s + repeat(" ", right);
    }

}
