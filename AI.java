import java.util.ArrayList;

import static java.lang.Math.min;
import static java.lang.Math.max;

/**
 * An Artificial Intelligence player that automatically makes its own moves.
 * @author Ryan Campbell
 */
public class AI extends Player{

    public AI(boolean playerColor, int depth) {
        super(playerColor);
        _depth = depth;
    }

    @Override
    public boolean isAuto() {
        return true;
    }

    public String getMove() {
        return "";
    }

    /** Returns an ArrayList<String> where each element is a possible move for CURRPLAYER.
     * Strings with more than 1 digits represent the moves where multiple divets are selected.
     */
    private ArrayList<String> legalMoves(ArrayList<Board> boards, ArrayList<String> legalMoves, boolean currPlayer) {
        String move;
        Board board;
        boolean continueRecursion = false;
        int listLength = legalMoves.size();
        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (int m = 0; m < listLength; m += 1) {
            if (!legalMoves.get(m).equals("")) {
                if (boards.get(m).checkValidMove(correctDivet(Integer.parseInt(legalMoves.get(m)) % 10, currPlayer))) {
                    boards.get(m).makeMove(correctDivet(Integer.parseInt(legalMoves.get(m)) % 10, currPlayer));
                    boards.set(m, boards.get(m));
                }
            }
            if (boards.get(m).isRedTurn() == currPlayer) {
                //move = legalMoves.remove(m);
                //board = boards.remove(m);
                move = legalMoves.get(m);
                board = boards.get(m);
                toBeRemoved.add(m);
                continueRecursion = true;
                for (int i = 1; i < 7; i += 1) {
                    if (board.divets()[correctDivet(i, currPlayer)] > 0) {
                        legalMoves.add(move + Integer.toString(i));
                        boards.add(new Board(board));
                    }
                }
            }
        }
        for (int m : toBeRemoved) {
            legalMoves.remove(m);
            boards.remove(m);
        }
        if (continueRecursion) {
            return legalMoves(boards, legalMoves, currPlayer);
        } else {
            return legalMoves;
        }
    }

    private int staticScore(Board board, int winningValue) {
        String winner = board.winnerString();
        if (winner.equals("")) {
            return board.redRocks() - board.blueRocks();
        } else if (winner.equals("RED WINS")) {
            return winningValue;
        } else if (winner.equals("BLUE WINS")) {
            return -winningValue;
        } else {
            return 0;
        }
    }

    private static int correctDivet(int divet, boolean playerColor) {
        if (playerColor) {
            return divet;
        } else {
            return 14 - divet;
        }
    }

    /** The depth of this AI. */
    private int _depth;
}
