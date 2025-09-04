package blackjack.Object;

import blackjack.Core.CardPrinter;

public class Dealer extends Participant {
    
    public void play(Deck deck)
    {
        while(hand.getPoint()<17)
        {
            hand.addCard(deck.drawCard());
        }
    }
 
    public void showHandHideFirst()
    {
        CardPrinter.printCardsHideFirst(hand.getHand(), true);
    }
    
}
