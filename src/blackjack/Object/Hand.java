package blackjack.Object;

import java.util.ArrayList;
import Enum.Rank;
import Enum.Suit;
import blackjack.Core.*;
import static blackjack.Core.CardPrinter.printCards;
public class Hand {

    private ArrayList<Card> hand = new ArrayList<>();

    public void addCard(Card card) {
        hand.add(card);
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public int getPoint() {
        int score = 0;
        int aceCount = 0;
        for (var card : hand) {
            score += card.getRank().getValue();
            if(card.getRank()==Rank.ACE) aceCount++;
        }
        
        while(aceCount>0 && score + 10 <= 21 )
        {
            score+=10;
            aceCount--;
        }
     
        return score;
    }
    
    public boolean isBlackJack()
    {
        return this.getPoint() == 21;
    }
    
    public boolean isNaturalBlackJack() {
        if (hand.size() > 2) {
            return false;
        }
        boolean hasAce = hand.get(0).getRank() == Rank.ACE || hand.get(1).getRank() == Rank.ACE;
        boolean hasTen = hand.get(0).getRank().getValue() == 10 || hand.get(1).getRank().getValue() == 10;
        
        return hasAce && hasTen;
    }

    public boolean isBust() {
        return getPoint() > 21;
    }
    
    public void clearHand()
    {
        hand.clear();
    }
    
}
