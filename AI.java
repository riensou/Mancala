import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.min;
import static java.lang.Math.max;

/**
 * An Artificial Intelligence player that automatically makes its own moves.
 * @author Ryan Campbell
 */
public class AI extends Player{

    /** A position magnitude indicating a win (for red if positive, blue
     *  if negative). */
    private static final int WINNING_VALUE = Integer.MAX_VALUE - 20;
    /** A magnitude greater than a normal value. */
    private static final int INFTY = Integer.MAX_VALUE;

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

    /** Returns a String of the best move.*/
    @Override
    public String findMove(Board board, Scanner userInput) {
        if (board.isRedTurn()) {
            System.out.print("RED: ");
        } else {
            System.out.print("BLUE: ");
        }
        _lastFoundMove = null;
        if (_playerColor) {
            minMax(board, _depth, true, 1, -INFTY, INFTY);
        } else {
            minMax(board, _depth, true, -1, -INFTY, INFTY);
        }
        System.out.println(_lastFoundMove);
        return _lastFoundMove;
    }

    /** Find a move from position BOARD and return its value, recording
     *  the move found in _foundMove iff SAVEMOVE. The move
     *  should have maximal value or have value > BETA if SENSE==1,
     *  and minimal value or value < ALPHA if SENSE==-1. Searches up to
     *  DEPTH levels.  Searching at level 0 simply returns a static estimate
     *  of the board value and does not set _foundMove. If the game is over
     *  on BOARD, does not set _foundMove. */
    private int minMax(Board board, int depth, boolean saveMove, int sense,
                       int alpha, int beta) {
        /* We use WINNING_VALUE + depth as the winning value so as to favor
         * wins that happen sooner rather than later (depth is larger the
         * fewer moves have been made. */
        if (depth == 0 || !board.winnerString().equals("")) {
            return staticScore(board, WINNING_VALUE + depth);
        }

        String best = null;
        int bestScore = 0;
        if (sense == 1) {
            bestScore = -INFTY;
            for (String M : legalMoves(board)) {
                Board nextBoard = new Board(board);
                nextBoard.makeMove(M);
                int response = minMax(nextBoard, depth - 1,
                        false, -1, alpha, beta);
                if (best == null) {
                    best = M;
                }
                if (response > bestScore) {
                    bestScore = response;
                    best = M;
                    alpha = max(alpha, bestScore);
                    if (alpha >= beta) {
                        if (saveMove) {
                            _lastFoundMove = M;
                        }
                        return bestScore;
                    }
                }
            }
        } else if (sense == -1) {
            bestScore = INFTY;
            for (String M : legalMoves(board)) {
                Board nextBoard = new Board(board);
                nextBoard.makeMove(M);
                int response = minMax(nextBoard, depth - 1,
                        false, 1, alpha, beta);
                if (best == null) {
                    best = M;
                }
                if (response < bestScore) {
                    bestScore = response;
                    best = M;
                    beta = min(beta, bestScore);
                    if (alpha >= beta) {
                        if (saveMove) {
                            _lastFoundMove = M;
                        }
                        return bestScore;
                    }
                }
            }
        }
        if (saveMove) {
            _lastFoundMove = best;
        }
        return bestScore;
    }

    public ArrayList<String> legalMoves(Board board) {
        ArrayList<Board> boards = new ArrayList<Board>();
        ArrayList<String> legalMoves = new ArrayList<String>();
        boards.add(board);
        legalMoves.add("");
        return legalMoves(boards, legalMoves, board.isRedTurn());
    }

    /** Returns an ArrayList<String> where each element is a possible move for CURRPLAYER.
     * Strings with more than 1 digits represent the moves where multiple divets are selected.
     */
    @Override
    public ArrayList<String> legalMoves(ArrayList<Board> boards, ArrayList<String> legalMoves, boolean currPlayer) {
        String move;
        Board board;
        boolean continueRecursion = false;
        int listLength = legalMoves.size();
        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (int m = 0; m < listLength; m += 1) {
            if (!legalMoves.get(m).equals("")) {
                if (boards.get(m).isRedTurn() == currPlayer 
                && boards.get(m).checkValidMove(correctDivet(Integer.parseInt(legalMoves.get(m).substring(legalMoves.get(m).length() - 1)) % 10, currPlayer))) {
                    boards.get(m).makeMove(correctDivet(Integer.parseInt(legalMoves.get(m).substring(legalMoves.get(m).length() - 1)) % 10, currPlayer));
                    boards.set(m, boards.get(m));
                }
            }
            if (boards.get(m).isRedTurn() == currPlayer && boards.get(m).inProgress()) {
                move = legalMoves.get(m);
                board = boards.get(m);
                toBeRemoved.add(0, m);
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
