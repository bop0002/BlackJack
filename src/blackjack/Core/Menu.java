package blackjack.Core;

import java.util.concurrent.TimeUnit;
import blackjack.Object.Player;
import Enum.*;
import blackjack.Object.PlayerSlot;
import java.util.ArrayList;

public class Menu {


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
        Terminal.clear();
        printAsciiArt(BLACKJACK_ASCII);

        System.out.println();
        System.out.println(
                "               "
                + Color.GREEN + "♠"
                + Color.RED + "   ♦"
                + Color.GREEN + "   ♣"
                + Color.RED + "   ♥"
                + Color.RESET
        );
        System.out.println();

        System.out.println(Color.GREEN + "================= MAIN MENU =================" + Color.RESET);
        System.out.println(Color.CYAN + "                 1. New Game" + Color.RESET);
        System.out.println(Color.CYAN + "                 2. Load" + Color.RESET);
        System.out.println(Color.CYAN + "                 3. Instruction" + Color.RESET);
        System.out.println(Color.CYAN + "                 4. Exit" + Color.RESET);
        System.out.println(Color.GREEN + "============================================" + Color.RESET);
    }

    public static PlayerSlot newGameInit() {
        int slotNum = -1;
        String name = "";
        Terminal.clear();
        printAsciiArt(BLACKJACK_ASCII);

        System.out.println();
        System.out.println(
                "               "
                + Color.GREEN + "♠"
                + Color.RED + "   ♦"
                + Color.GREEN + "   ♣"
                + Color.RED + "   ♥"
                + Color.RESET
        );
        System.out.println();

        System.out.println(Color.GREEN + "================= New Game =================" + Color.RESET);

        // Name
        try {
            System.out.print(Color.CYAN + "Enter player name: " + Color.RESET);
            name = Input.sc.nextLine().trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty!");
            }
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid name, using 'Default'." + Color.RESET);
            name = "Default";
        }

        // Slot
        try {
            System.out.print(Color.CYAN + "Enter save slot: " + Color.RESET);
            slotNum = Integer.parseInt(Input.sc.nextLine());
            if (slotNum <= 0) {
                throw new IllegalArgumentException("Slot must be > 0");
            }
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid slot number, using slot 1." + Color.RESET);
            slotNum = 1;
        }

        Player player = new Player(name, 1000);
        PlayerSlot slot = new PlayerSlot(slotNum, player);
        Terminal.clear();
        return slot;
    }

    private static void printAsciiArt(String[] art) {
        for (String line : art) {
            System.out.println(Color.YELLOW + line + Color.RESET);
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
            System.out.println(Color.GREEN + spaces + line + Color.RESET);
        }

        for (int i = 0; i < 4; i++) {
            String dots = ".".repeat(i);

            Terminal.clear();

            for (int j = 0; j < STARTING_ASCII.length; j++) {
                int padding = (width - STARTING_ASCII[j].length()) / 2;
                String spaces = " ".repeat(Math.max(0, padding));

                if (j == STARTING_ASCII.length - 1) {

                    System.out.println(Color.GREEN + spaces + STARTING_ASCII[j] + dots + Color.RESET);
                } else {
                    System.out.println(Color.GREEN + spaces + STARTING_ASCII[j] + Color.RESET);
                }
            }

            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public static void showGameMenu(Player player) {
        Terminal.clear();
        System.out.println(Color.GREEN + "============================================" + Color.RESET);
        System.out.println(Color.GREEN + "                 GAME MENU" + Color.RESET);
        System.out.println(Color.GREEN + "============================================" + Color.RESET);
        System.out.println();
        System.out.println(Color.CYAN + "        1. Bet and Play" + Color.RESET);
        System.out.println(Color.CYAN + "        2. Save and Exit" + Color.RESET);
        System.out.println();
        System.out.println(Color.GREEN + "Your Balance: " + Color.YELLOW + player.getBalance() + Color.RESET);
        System.out.println(Color.GREEN + "============================================" + Color.RESET);
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
        Terminal.clear();
        for (String line : INSTRUCTION_ASCII) {
            System.out.println(Color.GREEN + line + Color.RESET);
        }
        String temp = Input.sc.nextLine();
    }

    //SaveSlot
    public static PlayerSlot loadSaveSlot() throws InterruptedException {
        Terminal.clear();
        DataManager dataManager = new DataManager();
        PlayerSlot slot1 = new PlayerSlot(1, dataManager.loadSlot(1));
        PlayerSlot slot2 = new PlayerSlot(2, dataManager.loadSlot(2));
        PlayerSlot slot3 = new PlayerSlot(3, dataManager.loadSlot(3));
        showSlot(1, slot1.getPlayer());
        showSlot(2, slot2.getPlayer());
        showSlot(3, slot3.getPlayer());
        System.out.println("Enter number to choose slot: ");
        int playerChoice = Integer.parseInt(Input.sc.nextLine());
        switch (playerChoice) {
            case 1:
                Terminal.clear();
                return slot1;
            case 2:
                Terminal.clear();
                return slot2;
            case 3:
                Terminal.clear();
                return slot3;
            default:
                System.out.println("Slot doesn't exist");
                TimeUnit.MILLISECONDS.sleep(500);
                Terminal.clear();
                return null;
        }

    }

    public static void showSlot(int slotNum, Player player) {
        int width = 17;

        String line1 = String.format("Slot %d: %s", slotNum, player.getName());
        String line2 = String.format("Balance: $%d", player.getBalance());

        line1 = padRight(line1, width);
        line2 = padRight(line2, width);

        System.out.println(Color.GREEN + "┌" + "─".repeat(width) + "┐" + Color.RESET);
        System.out.println(Color.GREEN + "│" + Color.RESET + Color.YELLOW + line1 + Color.RESET + Color.GREEN + "│" + Color.RESET);
        System.out.println(Color.GREEN + "│" + Color.RESET + Color.YELLOW + line2 + Color.RESET + Color.GREEN + "│" + Color.RESET);
        System.out.println(Color.GREEN + "└" + "─".repeat(width) + "┘" + Color.RESET);
    }

    private static String padRight(String s, int n) {
        if (s.length() >= n) {
            return s;
        }
        return s + " ".repeat(n - s.length());
    }

}
