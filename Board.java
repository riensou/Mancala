/**
 * A board that keeps track of the current state of the game.
 * @author Ryan Campbell
 */
public class Board {

    /* Creates a standard Mancala board. */
    public Board() {
        setDivets();
    }

    /* Sets the number of rocks in each non-scoring divet to 4.
     * Updates the number of rocks in play.
     */
    public void setDivets() {
        _divets = new int[14];
        for (int i = 0; i < _divets.length; i+= 1) {
            if (i % 7 != 0) {
                _divets[i] = 4;
            }
        }
        updateRocksInPlay();
    }

    /** Updates the number of rocks in play. */
    public void updateRocksInPlay() {
        int total = 0;
        for (int i = 0; i < _divets.length; i+= 1) {
            if (i % 7 != 0) {
                total += _divets[i];
            }
        }
        _rocksInPlay = total;
    }

    /** Returns the number of rocks in play. */
    public int rocksInPlay() {
        return _rocksInPlay;
    }

    /** Returns the number of rocks in Red's scoring divet. */
    public int redRocks() {
        return _redRocks;
    }

    /** Increases the number of Red rocks by INCREMENT. */
    public void increaseRedRocks(int increment) {
        _redRocks += increment;
    }

    /** Returns the number of rocks in Blue's scoring divet. */
    public int blueRocks() {
        return _blueRocks;
    }

    /** Increases the number of Blue rocks by INCREMENT. */
    public void increaseblueRocks(int increment) {
        _blueRocks += increment;
    }

    /** Updates whose turn it is after a move has been made. */
    public void updateWhoseTurn() {
        _isRedTurn = !_isRedTurn;
    }

    /** Returns whether or not it is Red's turn. */
    public boolean isRedTurn() {
        return _isRedTurn;
    }

    /* The 14 divets in a board of Mancala (0-indexed). Divet 0 and 7 are 
     * the scoring divets for Red and Blue respectively.
     */
    private int[] _divets;

    /** The number of rocks in the non-scoring divets. */
    private int _rocksInPlay;

    /** The number of rocks in Red's scoring divet. */
    private int _redRocks;

    /** The number of rocks in Blue's scoring divet. */
    private int _blueRocks;

    /** Keeps track of the player's turn. True iff Red's turn and False iff
     * Blue's turn. Red's turn is by default at the start of the game.
     */
    private boolean _isRedTurn = true;

}
