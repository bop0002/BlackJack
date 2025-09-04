package blackjack.Core;

import blackjack.Object.*;
import java.util.concurrent.TimeUnit;

public class MainGame {

    public void startGame() throws InterruptedException {
        while (true) {
            Terminal.clear();
            Menu.showMainMenu();
            int playerChoice = Integer.parseInt(Input.sc.nextLine());
            switch (playerChoice) {
                case 1:
                    Terminal.clear();
                    Menu.startingAnimation();
                    Terminal.clear();
                    newGame();
                    break;
                case 2:
                    Terminal.clear();
                    Menu.Highscore();
                    break;
                case 3:
                    Terminal.clear();
                    Menu.showInstruction();
                    break;
                case 4:
                    Terminal.clear();
                    System.out.println("Out");
                    System.exit(0);
                default:
                    Terminal.clear();
                    System.out.println("Nhap cai khac con me may");
            }
        }
    }

    public void newGame() throws InterruptedException {
        Player player = new Player();
        Dealer dealer = new Dealer();
        RoundManager roundManager = new RoundManager(player, dealer);
        boolean playing = true;
        while (playing) {
            TimeUnit.MILLISECONDS.sleep(1000);
            Menu.showGameMenu(player.getBalance());
            try {
                int playerChoice = Integer.parseInt(Input.sc.nextLine());
                switch (playerChoice) {
                    case 1:
                        roundManager.bettingState();
                        roundManager.playingState();
                        break;
                    case 2:
                        Terminal.clear();
                        playing = false;
                        break;
                    default:
                        Terminal.clear();
                        System.out.println("cc");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Con me may");
            }
        }

    }

}
