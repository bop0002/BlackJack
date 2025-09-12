# Blackjack Game

This is a console-based card game for one player against the dealer.  
The game is played with a standard 52-card deck. Each round, the player places a bet and tries to beat the dealer by getting a hand value as close to 21 as possible without exceeding it.



## Built With
- Java 17



## How to Run
- Clone the repository
- Open dist/launhcer.bat


## UML




## Demo
![Demo](docs/Animation.gif)



## Screenshots
![Main Menu](docs/MainMenu.PNG)  
![Instruction](docs/Instruction.PNG)  
![Gameplay](docs/Gameplay.PNG)


## Project Structure
BlackJack/
├── Enum/ (GameResult, Rank, Suit)
├── blackjack/ (Main)
├── blackjack/Core/ (CardPrinter, DataManager, Input, MainGame, Menu, PlayerData, RoundManager, Terminal, UIManager)
└── blackjack/Object/ (BettingAccount, Card, Dealer, Deck, Hand, Participant, Player, PlayerSlot)
