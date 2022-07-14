/**
 * A board that keeps track of the current state of the game.
 * @author Ryan Campbell
 */
public class Board {

    ///** Creates a default Mancala board on CAPTURE mode. */
    //public Board() {
    //    new Board("CAPTURE");
    //    _inProgress = true;
    //}

    /* Creates a Mancala board with mode MODE. */
    public Board(String mode) {
        setDivets();
        _mode = mode;
        _inProgress = true;
    }

    /** String representation of the Board. */
    public String toString() {
        String boardString = "";
        boardString += "       \u001B[34m" + formatNum(_divets[0]) + "\u001B[0m\n";
        for (int i = 1; i < _divets.length / 2; i += 1) {
            boardString += "(" + Integer.toString(i) + ") " + formatNum(_divets[i]) + " || " 
            + formatNum(_divets[14 - i]) + "\n";
        }
        boardString += "       \u001B[31m" + formatNum(_divets[7]) + "\u001B[0m\n";
        return boardString;
    }

    /** Helper function for toString method. */
    private static String formatNum(int i) {
        if (i >= 10) {
            return Integer.toString(i);
        } else {
            return "0" + Integer.toString(i);
        }
    }

    /** Returns true if the divet has rocks. */
    public boolean checkValidMove(int divet) {
        return (_divets[divet] > 0) && (divet % 7 != 0);
    }

    /** Move logic that can be called regardless of the
     * current game mode.
     */
    public void makeMove(int divet) {
        if (!checkValidMove(divet)) {
            System.out.println("Invalid Move");
            return;
        }
        if (_mode.equals("CAPTURE")) {
            makeMoveCapture(divet);
        } else {
            makeMoveAvalanche(divet);
        }
        updateRocksInPlay();
    }

    /** Move logic for the Capture rules of the game. */
    public void makeMoveCapture(int divet) {
        int moveLength = _divets[divet];

        _divets[divet] = 0;
        while (moveLength > 0) {
            divet = nextDivet(divet);
            _divets[divet] += 1;
            moveLength -= 1;
        }
        if ((divet == 7 && _isRedTurn) || (divet == 0 && !_isRedTurn)) {
            return;
        }
        else if (canCapture(divet)) {
            int captured = 0;
            captured += _divets[divet] + _divets[reflectedDivet(divet)];
            _divets[currScoringDivet()] += captured;
            _divets[divet] = 0;
            _divets[reflectedDivet(divet)] = 0;
        }
        updateWhoseTurn();
    }

    /** Move logic for the Avalanche rules of the game. */
    public void makeMoveAvalanche(int divet) {
        int moveLength = _divets[divet];

        _divets[divet] = 0;
        while (moveLength > 0) {
            divet = nextDivet(divet);
            _divets[divet] += 1;
            moveLength -= 1;
            if (canAvalanche(divet, moveLength)) {
                moveLength = _divets[divet];
                _divets[divet] = 0;
            }
        }
        if ((divet == 7 && _isRedTurn) || (divet == 0 && !_isRedTurn)) {
            return;
        }
        updateWhoseTurn();
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

    /** Returns the next divet after DIVET. */
    public int nextDivet(int divet) {
        int next = (divet + 1) % 14;
        if ((isRedTurn() && next == 0) || (!isRedTurn() && next == 7)) {
            next += 1;
        }
        return next;
    }

    /** Returns the reflected divet on the other side of the board. */
    public int reflectedDivet(int divet) {
        return (14 - divet) % 14;
    }

    /** Returns true iff DIVET is on the current player's side. */
    public boolean currPlayerDivet(int divet) {
        return ((isRedTurn() && divet >= 1 && divet <= 7)
         || (!isRedTurn() && (divet >= 8 || divet == 0)));
    }

    /** Returns true iff the divet across from DIVET is not empty. */
    public boolean reflectedDivitNotEmpty(int divet) {
        return _divets[reflectedDivet(divet)] > 0;
    }

    /** Returns true if the current player will capture the opponents rocks.
     * Capture mode only.
     */
    public boolean canCapture(int divet) {
        return _divets[divet] == 1 && currPlayerDivet(divet) && reflectedDivitNotEmpty(divet);
    }

    /** Returns true if the current player will avalanche.
     * Avalanche mode only.
     */
    public boolean canAvalanche(int divet, int moveLength) {
        return _divets[divet] != 1 && (divet % 7) != 0 && moveLength == 0;
    }

    /** Returns the location of the scoring divet for whosever turn it is. */
    public int currScoringDivet() {
        if (isRedTurn()) {
            return 7;
        } else {
            return 0;
        }
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

    /** Returns whether or not the game is still being played. */
    public boolean inProgress() {
        return _inProgress;
    }

    /** Sets _inProgress to false when the game is over. */
    public void completed() {
        _inProgress = false;
    }

    /* The 14 divets in a board of Mancala (0-indexed). Divet 0 and 7 are 
     * the scoring divets for Blue and Red respectively.
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

    /** Keeps track of the mode of the game.
     * CAPTURE is capture mode. (default)
     * AVALANCHE is avalanche mode. 
     */
    private String _mode;

    /** Returns true iff the current board has no winner. */
    private boolean _inProgress;
}
