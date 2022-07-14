import java.util.Scanner;

/**
 * The logic of the game.
 * @author Ryan Campbell
 */
public class Game {

    /** Starts a game of Mancala. */
    public Game(String mode) {
        _board = new Board(mode);
        _redPlayer = new Player(true);
        _bluePlayer = new Player(false);
    }



    public void play() {
        try (Scanner userInput = new Scanner(System.in)) {
            while (_board.inProgress()) {
                if (_board.isRedTurn()) {
                    String input = userInput.nextLine();
                    System.out.println("RED: " + input);
                    _board.makeMove(Integer.valueOf(input));
                    System.out.println(_board);
                } else {
                    String input = userInput.nextLine();
                    System.out.println("BLUE: " + input);
                    _board.makeMove(blueDivet(Integer.valueOf(input)));
                    System.out.println(_board);
                }
            }
        }
    }

    private static int blueDivet(int i) {
        return 14 - i;
    }

    /** The board of this game of Mancala. */
    private Board _board;

    /** The red player of this game of Mancala. */
    private Player _redPlayer;

    /** The blue player of this game of Mancala. */
    private Player _bluePlayer;
}
