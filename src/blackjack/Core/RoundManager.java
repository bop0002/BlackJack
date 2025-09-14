package blackjack.Core;

import Enum.Color;
import blackjack.Object.*;
import Enum.GameResult;
import java.util.concurrent.TimeUnit;

public class RoundManager {

    public final Player player;
    public final Dealer dealer;
    private GameResult result;
    private long pot = 0;
    private Deck deck;

    public RoundManager(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        pot = 0;
        result = GameResult.NONE;
    }

    public boolean bettingState() throws InterruptedException {
        while (true) {
            if (player.getBalance() <= 0) {
                System.out.println(Color.GREEN + "Not enough money");
                TimeUnit.MILLISECONDS.sleep(500);
                return false;
            }
            System.out.print(Color.GREEN + "Enter bet: " + Color.YELLOW);
            try {
                long bet = Long.parseLong(Input.sc.nextLine());
                System.out.print(Color.RESET);
                if (player.getBalance() >= bet) {
                    pot = bet * 2;
                    player.placeBet(bet);
                    //dealer.placeBet(bet);
                    return true;
                } else {
                    System.out.println(Color.RESET + "Invalid bet" + Color.RESET);
                    TimeUnit.MILLISECONDS.sleep(500);
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println(Color.RESET + "Please enter a valid number." + Color.RESET);
                TimeUnit.MILLISECONDS.sleep(500);
                return false;
            }
        }
    }

    public void playingState() {
        result = GameResult.NONE;
        boolean playingTurn = true;
        deck = new Deck();
        deck.shuffle();
        dealInitial();
        checkNaturalBlackJack();

        switch (result) {
            case PLAYER_WIN:
                endGameState();
                playingTurn = false;
                break;
            case DEALER_WIN:
                endGameState();
                playingTurn = false;
                break;
            case TIE:
                endGameState();
                playingTurn = false;
                break;
            case NONE:
                break;
            default:
                System.out.println("error check natural blackjack");
        }

        while (playingTurn) {

            UIManager.showStatus(player, dealer, deck);
            UIManager.showAction();

            int playerChoice = playerAction();

            switch (playerChoice) {
                case 1:
                    playerHit();
                    if (player.isBust()) {
                        result = GameResult.DEALER_WIN;
                        playingTurn = false;
                        break;
                    }
                    if (player.isBlackJack()) {
                        result = GameResult.PLAYER_WIN;
                        playingTurn = false;
                        break;
                    }
                    break;
                case 2:
                    dealer.play(deck);
                    if (dealer.isBust()) {
                        result = GameResult.PLAYER_WIN;
                        playingTurn = false;
                        break;
                    }
                    calEndGame();
                    playingTurn = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    result = GameResult.NONE;
                    break;
            }
            if (playingTurn == false) {
                endGameState();
                return;
            }
        }
    }

    private void endGameState() {

        UIManager.showStatus(player, dealer, deck);

        switch (result) {
            case PLAYER_WIN:
                UIManager.playerWin();
                player.payOut(pot);
                break;
            case DEALER_WIN:
                UIManager.dealerWin();
                //dealer.payOut(pot);
                break;
            case TIE:
                UIManager.tie();
                player.payOut(pot / 2);
                //dealer.payOut(pot / 2);
                break;
            default:
                System.out.println("endState error");
                break;
        }

        pot = 0;
        dealer.clearHand();
        player.clearHand();

        System.out.println(Color.CYAN + "Press enter continue....");
        String temp = Input.sc.nextLine();
    }

    private void playerHit() {
        player.addCard(deck.drawCard());
    }

    private int playerAction() {
        while (true) {
            System.out.print("Your choice: ");
            String input = Input.sc.nextLine();
            try {
                int choice = Integer.parseInt(input.trim());
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }

    private void dealInitial() {
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    private void checkNaturalBlackJack() {
        if (player.isNaturalBlackJack() && dealer.isNaturalBlackJack()) {
            result = GameResult.TIE;
        }
        if (player.isNaturalBlackJack()) {
            result = GameResult.PLAYER_WIN;
        }
        if (dealer.isNaturalBlackJack()) {
            result = GameResult.DEALER_WIN;
        }
    }

    private void calEndGame() {
        int playerPoint = player.getPoint();
        int dealerPoint = dealer.getPoint();

        if (playerPoint < dealerPoint) {
            result = GameResult.DEALER_WIN;
        } else if (playerPoint > dealerPoint) {
            result = GameResult.PLAYER_WIN;
        } else if (playerPoint == dealerPoint) {
            result = GameResult.TIE;
        }
    }
}
