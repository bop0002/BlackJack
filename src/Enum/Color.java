package Enum;


public enum Color {
    RESET("\u001B[0m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    CYAN("\u001B[36m"),
    RED("\u001B[31m");
    
    private final String colorS;
    Color(String colorS)
    {
        this.colorS = colorS;
    }
    
    
}
