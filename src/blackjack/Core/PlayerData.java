package blackjack.Core;

public class PlayerData {
    private String name;
    private long balance;

    public PlayerData(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() { return name; }
    public long getBalance() { return balance; }
}
