package blackjack.Core;

import blackjack.Object.*;
import java.util.concurrent.TimeUnit;

public class MainGame {

    public void Start() throws InterruptedException {
        while (true) {
            Menu.showMainMenu();
            int playerChoice;

            try {
                playerChoice = Integer.parseInt(Input.sc.nextLine());
            } catch (NumberFormatException e) {
                Terminal.clear();
                System.out.println("Invalid choice");
                continue;
            }

            switch (playerChoice) {
                case 1:
                    PlayerSlot slotNG = Menu.newGameInit();
                    Menu.startingAnimation();
                    startGame(slotNG);
                    break;

                case 2:
                    PlayerSlot slot = Menu.loadSaveSlot();
                    if (slot != null) {
                        Menu.startingAnimation();
                        startGame(slot);
                    }
                    break;

                case 3:
                    Menu.showInstruction();
                    break;

                case 4:
                    Terminal.clear();
                    return;

                default:
                    Terminal.clear();
                    System.out.println("Invalid choice");
            }
        }
    }

    public void startGame(PlayerSlot playerSlot) throws InterruptedException {
        Player player = playerSlot.getPlayer();
        Dealer dealer = new Dealer();
        DataManager dataManager = new DataManager();
        RoundManager roundManager = new RoundManager(player, dealer);
        boolean playing = true;
        while (playing) {
            TimeUnit.MILLISECONDS.sleep(1000);
            Menu.showGameMenu(player);
            try {
                int playerChoice = Integer.parseInt(Input.sc.nextLine());
                switch (playerChoice) {
                    case 1:
                        if (roundManager.bettingState()) {
                            roundManager.playingState();
                        }
                        break;
                    case 2:
                        Terminal.clear();
                        dataManager.saveSlot(playerSlot.getSlotNum(), player);
                        playing = false;
                        break;
                    default:
                        Terminal.clear();
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }

    }

}
