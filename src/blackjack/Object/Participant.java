package blackjack.Object;

import blackjack.Core.CardPrinter;

public abstract class Participant {

    protected Hand hand = new Hand();
    protected BettingAccount bettingAccount = new BettingAccount();
    
    public long getBalance()
    {
        return this.bettingAccount.getBalance();
    }
    
    public void payOut(long pot)
    {
        bettingAccount.payOut(pot);
    }
    
    public void playBet(long bet) //name changeable
    {
        bettingAccount.playBet(bet);
    }
    public void showAllHand()
    {
        CardPrinter.printCards(hand.getHand(), null);
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
    
    public boolean isBlackJack()
    {
        return hand.isBlackJack();
    }
    
    public boolean isNaturalBlackJack() {
        return hand.isNaturalBlackJack();
    }
    
    public void clearHand()
    {
        hand.clearHand();
        
    }
    
}
