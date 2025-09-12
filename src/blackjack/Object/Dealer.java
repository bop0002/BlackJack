package blackjack.Object;

import blackjack.Core.CardPrinter;

public class Dealer extends Participant {

    public Dealer() {
        super();
        this.name = "Dealer";
    }

    public void play(Deck deck) {
        while (hand.getPoint() < 17) {
            hand.addCard(deck.drawCard());
        }
    }


}
