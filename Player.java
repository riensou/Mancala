/**
 * A player of the game.
 * @author Ryan Campbell
 */
public class Player {
    /** Creates a red or blue player. */
    public Player(boolean playerColor) {
        _playerColor = playerColor;
    }

    public boolean getColor() {
        return _playerColor;
    }

    /** True iff player is red,
     * False iff player is blue.
     */
    private boolean _playerColor;
}
