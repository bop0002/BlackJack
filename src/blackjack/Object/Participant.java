package blackjack.Object;

import blackjack.Core.CardPrinter;
import java.util.ArrayList;

public abstract class Participant {

    protected String name;
    protected Hand hand;
    protected BettingAccount bettingAccount;

    public Participant() {
        this.hand = new Hand();
        this.bettingAccount = new BettingAccount();
    }

    public String getName() {
        return this.name;
    }

    public long getBalance() {
        return this.bettingAccount.getBalance();
    }

    public void payOut(long pot) {
        bettingAccount.payOut(pot);
    }

    public void placeBet(long bet) //name changeable
    {
        bettingAccount.placeBet(bet);
    }
    
    public ArrayList<Card> getCard()
    {
        return hand.getHand();
    }
    

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getPoint() {
        return hand.getPoint();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isNaturalBlackJack() {
        return hand.isNaturalBlackJack();
    }

    public void clearHand() {
        hand.clearHand();

    }

}
