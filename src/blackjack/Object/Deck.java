package blackjack.Object;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import Enum.Rank;
import Enum.Suit;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck()
    {
        for(Rank rank:Rank.values())
        {
            for(Suit suit:Suit.values())
            {
                deck.add(new Card(rank,suit));
            }
        }
    }
    
    public int getSize()
    {
        return deck.size();
    }
    
    public void shuffle()
    {
        Collections.shuffle(deck);
    }
    
    public Card drawCard()
    {
        int index = ThreadLocalRandom.current().nextInt(deck.size());
        Card card = deck.get(index);
        deck.remove(index);
        return card;
    }
    
}
