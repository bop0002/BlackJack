package blackjack.Core;

import java.util.concurrent.TimeUnit;
import Enum.Rank;
import Enum.Suit;

public class Menu {

    //color
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";

    //print game name
    private static final String[] BLACKJACK_ASCII = {
        " /$$$$$$$ /$$       /$$$$$$   /$$$$$$ /$$   /$$    /$$$$$  /$$$$$$   /$$$$$$ /$$   /$$",
        "| $$__  $| $$      /$$__  $$ /$$__  $| $$  /$$/   |__  $$ /$$__  $$ /$$__  $| $$  /$$/",
        "| $$  \\ $| $$     | $$  \\ $$| $$  \\__| $$ /$$/       | $$| $$  \\ $$| $$  \\__| $$ /$$/ ",
        "| $$$$$$$| $$     | $$$$$$$$| $$     | $$$$$/        | $$| $$$$$$$$| $$     | $$$$$/ ",
        "| $$__  $| $$     | $$__  $$| $$     | $$  $$   /$$  | $$| $$__  $$| $$     | $$  $$ ",
        "| $$  \\ $| $$     | $$  | $$| $$    $| $$\\  $$ | $$  | $$| $$  | $$| $$    $| $$\\  $$ ",
        "| $$$$$$$| $$$$$$$| $$  | $$|  $$$$$$| $$ \\  $$|  $$$$$$/| $$  | $$|  $$$$$$| $$ \\  $$",
        "|_______/|________|__/  |__/ \\______/|__/  \\__/ \\______/ |__/  |__/\\______/ |__/  \\__/"
    };

    public static void gameName() {
        printAsciiArt(BLACKJACK_ASCII);
    }

    public static void showMainMenu() {
        printAsciiArt(BLACKJACK_ASCII);

        System.out.println();
        System.out.println(
                "               "
                + GREEN + "♠"
                + RED + "   ♦"
                + GREEN + "   ♣"
                + RED + "   ♥"
                + RESET
        );
        System.out.println();

        System.out.println(GREEN + "================= MAIN MENU =================" + RESET);
        System.out.println(CYAN + "                 1. New Game" + RESET);
        System.out.println(CYAN + "                 2. Settings" + RESET);
        System.out.println(CYAN + "                 3. Instruction" + RESET);
        System.out.println(CYAN + "                 4. Exit" + RESET);
        System.out.println(GREEN + "============================================" + RESET);
    }

    private static void printAsciiArt(String[] art) {
        for (String line : art) {
            System.out.println(YELLOW + line + RESET);
        }
    }

    //print starting
    private static final String[] STARTING_ASCII = {
        "███████╗ ████████╗ █████╗ ██████╗ ████████╗██╗███╗   ██╗ ██████╗ ",
        "██╔════╝ ╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝██║████╗  ██║██╔════╝ ",
        "███████╗    ██║   ███████║██████╔╝   ██║   ██║██╔██╗ ██║██║  ███╗",
        "╚════██║    ██║   ██╔══██║██╔══██╗   ██║   ██║██║╚██╗██║██║   ██║",
        "███████║    ██║   ██║  ██║██║  ██║   ██║   ██║██║ ╚████║╚██████╔╝",
        "╚══════╝    ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝╚═╝  ╚═══╝ ╚═════╝ "
    };

    public static void startingAnimation() throws InterruptedException {
        int width = 100;

        for (String line : STARTING_ASCII) {
            int padding = (width - line.length()) / 2;
            String spaces = " ".repeat(Math.max(0, padding));
            System.out.println(GREEN + spaces + line + RESET);
        }

        for (int i = 0; i < 4; i++) {
            String dots = ".".repeat(i);

            Terminal.clear();

            for (int j = 0; j < STARTING_ASCII.length; j++) {
                int padding = (width - STARTING_ASCII[j].length()) / 2;
                String spaces = " ".repeat(Math.max(0, padding));

                if (j == STARTING_ASCII.length - 1) {

                    System.out.println(GREEN + spaces + STARTING_ASCII[j] + dots + RESET);
                } else {
                    System.out.println(GREEN + spaces + STARTING_ASCII[j] + RESET);
                }
            }

            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public static void showGameMenu(long balance) {
        Terminal.clear();
        System.out.println(GREEN + "============================================" + RESET);
        System.out.println(GREEN + "                 GAME MENU" + RESET);
        System.out.println(GREEN + "============================================" + RESET);
        System.out.println();
        System.out.println(CYAN + "        1. Bet and Play" + RESET);
        System.out.println(CYAN + "        2. Exit" + RESET);
        System.out.println();
        System.out.println(GREEN + "Your Balance: " + YELLOW + balance + RESET);
        System.out.println(GREEN + "============================================" + RESET);
    }

    //Instruction
    private static final String[] INSTRUCTION_ASCII = {
        "============================================================",
        "                      BLACKJACK GUIDE                      ",
        "============================================================",
        "",
        "1. Your objective is to finish with a hand closer to 21",
        "   than the dealer, without going over.",
        "",
        "2. J, Q, K are worth 10 points. An Ace can count as 1 or 11.",
        "",
        "3. 'Hit' means draw another card.",
        "   'Stand' means stop drawing and keep your total.",
        "",
        "4. If your hand goes above 21, you bust and instantly lose.",
        "",
        "5. A natural 21 with your first two cards is Blackjack.",
        "",
        "6. If your hand beats the dealer's, you win double your bet.",
        "",
        "------------------------------------------------------------",
        "              ( Press any key to continue... )",
        "============================================================"
    };

    public static void showInstruction() {
        for (String line : INSTRUCTION_ASCII) {
            System.out.println(GREEN + line + RESET);
        }
        String temp = Input.sc.nextLine();
    }

    public static void Highscore() {
        for (Suit s : Suit.values()) {
            System.out.println(s + " => " + s.getArt());
        }
        for (Rank r : Rank.values()) {
            System.out.println(r + " => " + r.getArt());
        }
        String temp = Input.sc.nextLine();
    }

}
