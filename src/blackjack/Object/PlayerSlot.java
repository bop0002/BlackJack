package blackjack.Object;

public class PlayerSlot {

    private final int slotNum;
    private Player player;

    public PlayerSlot(int slotNum, Player player) {
        this.slotNum = slotNum;
        this.player = player;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public Player getPlayer() {
        return player;
    }
}


