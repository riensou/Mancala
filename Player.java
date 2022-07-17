import java.util.ArrayList;
import java.util.Scanner;

/**
 * A player of the game.
 * @author Ryan Campbell
 */
public class Player {
    
    /** Creates a red or blue player. */
    public Player(boolean playerColor) {
        _playerColor = playerColor;

    }

    public boolean isAuto() {
        return false;
    }

    public boolean getColor() {
        return _playerColor;
    }

    public ArrayList<String> legalMoves(ArrayList<Board> boards, ArrayList<String> legalMoves, boolean currPlayer) {
        return legalMoves;
    }

    public String findMove(Board board, Scanner userInput) {
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
            (board.checkValidMove(14 - inputValue) && !board.isRedTurn())));
        } catch (NumberFormatException e) {
            valid = false;
        }
        if (!valid) {
            System.out.println("\u001B[33mInvalid Move\u001B[0m\n");
            input = findMove(board, userInput);
        }
        return input;
    }

    /** True iff player is red,
     * False iff player is blue.
     */
    protected boolean _playerColor;

    /** The most recently found move of this AI. */
    protected String _lastFoundMove;
}
