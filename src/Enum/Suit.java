package Enum;

public enum Suit {

    CLUBS("♣"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♠");

    private final String art;
    
    Suit(String art) {
        this.art = art;
    }
    
    public String getArt()
    {
        return art;
    }
    
}
