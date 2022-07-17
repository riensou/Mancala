import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The logic of the game.
 * @author Ryan Campbell
 */
public class Game {

    /** Starts a game of Mancala. */
    public Game(String mode) {
        _board = new Board(mode);
        _redPlayer = new AI(true, 7);
        _bluePlayer = new AI(false, 7);
    }


    /** Plays a game of Mancala. */
    public void play() {
        System.out.println(_board);
        try (Scanner userInput = new Scanner(System.in)) {
            while (_board.inProgress()) {
                if (_board.isRedTurn()) {
                    String input = getMove(_redPlayer, userInput, _board);
                    _board.makeMove(input);
                    System.out.println(_board);
                } else {
                    String input = getMove(_bluePlayer, userInput, _board);
                    _board.makeMove(input);
                    System.out.println(_board);
                }
            }
            System.out.println(_board.winnerString());
        }
    }

    private static String getMove(Player player, Scanner userInput, Board board) {
        return player.findMove(board, userInput);
    }

    /** The board of this game of Mancala. */
    private Board _board;

    /** The red player of this game of Mancala. */
    private Player _redPlayer;

    /** The blue player of this game of Mancala. */
    private Player _bluePlayer;
}
