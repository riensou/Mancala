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
        _redPlayer = new Player(true);
        _bluePlayer = new Player(false);
    }

    public Game(String mode, String AI_1, String depth_1) {
        _board = new Board(mode);
        boolean AI1 = stringToBoolean(AI_1);
        int depth1 = stringToInt(depth_1);
        if (AI1) {
            _redPlayer = new AI(AI1, depth1); 
            _bluePlayer = new Player(false);
        } else {
            _redPlayer = new Player(true);
            _bluePlayer = new AI(AI1, depth1);
        }
    }

    public Game(String mode, String AI_1, String depth_1, String AI_2, String depth_2) {
        _board = new Board(mode);
        boolean AI1 = stringToBoolean(AI_1);
        int depth1 = stringToInt(depth_1);
        boolean AI2 = stringToBoolean(AI_2);
        int depth2 = stringToInt(depth_2);
        if (AI1 == AI2) {
            System.out.println("\u001B[33mInvalid Player Colors\u001B[0m");
            System.exit(1);
        } else if (AI1) {
            _redPlayer = new AI(AI1, depth1); 
            _bluePlayer = new AI(AI2, depth2);
        } else {
            _redPlayer = new AI(AI2, depth2); 
            _bluePlayer = new AI(AI1, depth1);
        }

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

    private static int stringToInt(String depth) {
        try {
            int inputValue = Integer.parseInt(depth);
            return inputValue;
        } catch (NumberFormatException e) {
            System.out.println("\u001B[33mInvalid Depth: " + depth + "\u001B[0m");
            System.exit(1);
        }
        return -1;
    }

    private static boolean stringToBoolean(String AI) {
        if (AI.equals("RED")) {
            return true;
        } else if (AI.equals("BLUE")) {
            return false;
        } else {
            System.out.println("\u001B[33mInvalid Player Color: " + AI + "\u001B[0m");
            System.exit(1);
        }
        return false;
    }

    /** The board of this game of Mancala. */
    private Board _board;

    /** The red player of this game of Mancala. */
    private Player _redPlayer;

    /** The blue player of this game of Mancala. */
    private Player _bluePlayer;
}
