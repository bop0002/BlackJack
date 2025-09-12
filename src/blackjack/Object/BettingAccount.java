package blackjack.Object;

public class BettingAccount {

    private long balance;

    public BettingAccount() {
        this.balance = 100;
    }

    public BettingAccount(long balance) {
        this.balance = balance;
    }

    public void payOut(long pot) {
        if (pot <= 0) {
            throw new IllegalArgumentException("Pot less than zero");
        }
        balance += pot;
    }

    public void playBet(long bet) {
        if (balance < bet) {
            throw new IllegalStateException("Balance not enough to place bet");
        }
        balance -= bet;
    }

    public long getBalance() {
        return this.balance;
    }

}
