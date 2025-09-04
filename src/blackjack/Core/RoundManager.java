package blackjack.Core;

import blackjack.Object.*;
import Enum.GameResult;

public class RoundManager {

    public final Player player;
    public final Dealer dealer;
    private GameResult result;
    private long pot = 0;
    public Deck deck;

    public RoundManager(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        pot = 0;
        result = GameResult.NONE;
    }

    public void bettingState() {
        while (true) {
            System.out.print(UIManager.getGreen() + "Enter bet: " + UIManager.getYellow());
            try {
                long bet = Long.parseLong(Input.sc.nextLine());
                System.out.print(UIManager.getReset());
                if (player.getBalance() >= bet && dealer.getBalance() >= bet) {
                    pot = bet * 2;
                    player.playBet(bet);
                    dealer.playBet(bet);
                    break;
                } else {
                    System.out.println(UIManager.getReset() + "Invalid bet" + UIManager.getReset());
                }
            } catch (NumberFormatException e) {
                System.out.println(UIManager.getReset() + "Please enter a valid number." + UIManager.getReset());
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
                    System.out.println("Nhap cai khac con me may");
                    result = GameResult.NONE;
                    break;
            }
            if (playingTurn == false) {
                endGameState();
                return;
            }
        }
    }

    void endGameState() {

        switch (result) {
            case PLAYER_WIN:
                UIManager.playerWin();
                player.payOut(pot);
                break;
            case DEALER_WIN:
                UIManager.dealerWin();
                dealer.payOut(pot);
                break;
            case TIE:
                UIManager.tie();
                player.payOut(pot / 2);
                dealer.payOut(pot / 2);
                break;
            default:
                System.out.println("endState error");
                break;
        }
        UIManager.showStatus(player, dealer, deck);

        dealer.clearHand();
        player.clearHand();

        System.out.println(UIManager.getCyan() + "Press enter continue....");
        String temp = Input.sc.nextLine();
    }

    public void playerHit() {
        player.addCard(deck.drawCard());
    }

    public int playerAction() {
        int playerChoice = Integer.parseInt(Input.sc.nextLine());
        return playerChoice;
    }

    public void dealInitial() {
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    public void checkNaturalBlackJack() {
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

    public void calEndGame() {
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
