# Mancala

Mancala is a two-player game. The objective is to score the most rocks by the end of the game. 
As shown below, the board consists of 14 divets, 6 on each player's side and 2 on the ends. Players take turns moving the rocks on their side until the game is over.
There are many modes in Mancala, but so far, I have implemented two. For more specific rules read here https://en.wikipedia.org/wiki/Mancala.

![50030 8442daac](https://user-images.githubusercontent.com/90002238/179395727-f84b217f-e072-4057-b6e0-07af6c6eb943.jpg)

Capture Mode:
- Game ends when there are 0 rocks on one side 
- All rocks on other player's side get transferred to their scoring divet when game ends
- If the final rock lands on an empty divet, capture the opponent's correpsonding rocks
- Get an extra turn if the final rock lands in the scoring divet

Avalanche Mode:
- Game ends when there are 0 rocks on one side 
- Rocks on other player's side do not get transferred to their scoring divet when game ends
- Turn only ends if the final rock lands on an empty divet (do not capture)
- For every last rock, if it lands in a divet with more rocks, pick it up and keep going

Commands:

java Main - plays a capture mode game of Mancala between two players

java Main (mode) - plays a (mode) game of Mancala between two players

java Main (mode) (AI AI depth) - plays a (mode) game of Mancala between a player an AI

java Main (mode) (AI1 AI1 depth) (AI2 AI2 depth) - plays a (mode) game of Mancala between two AIs.
