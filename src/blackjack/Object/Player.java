package blackjack.Object;


public class Player extends Participant{

    public Player(String name,long balance)
    {
        super();
        this.name = name;
        this.bettingAccount = new BettingAccount(balance);
    }
    
}
