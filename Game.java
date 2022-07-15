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


    /** Plays a game of Mancala. */
    public void play() {
        System.out.println(_board);
        try (Scanner userInput = new Scanner(System.in)) {
            while (_board.inProgress()) {
                if (_board.isRedTurn()) {
                    String input = getMove(_redPlayer, userInput, _board);
                    _board.makeMove(Integer.valueOf(input));
                    System.out.println(_board);
                } else {
                    String input = getMove(_bluePlayer, userInput, _board);
                    _board.makeMove(blueDivet(Integer.valueOf(input)));
                    System.out.println(_board);
                }
            }
            System.out.println(_board.winnerString());
        }
    }

    /** Returns the move number for a blue divet. */
    private static int blueDivet(int i) {
        return 14 - i;
    }

    private static String getMove(Player player, Scanner userInput, Board board) {
        if (player.isAuto()) {
            //get AI generated move somehow
            return "THIS SHOULDN'T HAPPEN RIGHT NOW";
        } else {
            return getValidEntry(userInput, board);
        }
    }

    /** Returns a valid move string for a manual player. */
    private static String getValidEntry(Scanner userInput, Board board) {
        if (board.isRedTurn()) {
            System.out.print("RED: ");
        } else {
            System.out.print("BLUE: ");
        }
        String input = userInput.nextLine();
        boolean valid;
        try {
            int inputValue = Integer.parseInt(input);
            valid = ((inputValue >= 1) && (inputValue < 7) 
            && ((board.checkValidMove(inputValue) && board.isRedTurn()) | 
            (board.checkValidMove(blueDivet(inputValue)) && !board.isRedTurn())));
        } catch (NumberFormatException e) {
            valid = false;
        }
        if (!valid) {
            System.out.println("\u001B[33mInvalid Move\u001B[0m\n");
            input = getValidEntry(userInput, board);
        }
        return input;
    }

    /** The board of this game of Mancala. */
    private Board _board;

    /** The red player of this game of Mancala. */
    private Player _redPlayer;

    /** The blue player of this game of Mancala. */
    private Player _bluePlayer;
}
