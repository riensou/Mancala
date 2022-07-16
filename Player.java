import java.util.ArrayList;

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

    /** True iff player is red,
     * False iff player is blue.
     */
    protected boolean _playerColor;
}
