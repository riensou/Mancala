import java.util.Scanner;

/**
 * The logic of the game.
 * @author Ryan Campbell
 */
public class Game {

    /** Starts a game of Mancala. */
    public Game() {
        _board = new Board();
        _redPlayer = new Player(true);
        _bluePlayer = new Player(false);
    }



    public void play() {
        try (Scanner userInput = new Scanner(System.in)) {
            while (_board.inProgress()) {
                if (_board.isRedTurn()) {
                    String input = userInput.nextLine();
                    System.out.println("RED: " + input);
                    _board.updateWhoseTurn(); // Do this in Board.java not here
                } else {
                    String input = userInput.nextLine();
                    System.out.println("BLUE: " + input);
                    _board.updateWhoseTurn(); // Do this in Board.java not here
                }
            }
        }
    }

    /** The board of this game of Mancala. */
    private Board _board;

    /** The red player of this game of Mancala. */
    private Player _redPlayer;

    /** The blue player of this game of Mancala. */
    private Player _bluePlayer;
}
