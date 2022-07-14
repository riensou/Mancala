/**
 * The main program for running Mancala.
 * @author Ryan Campbell
 */
public class Main {
    /** Usage: java Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        Game newGame = new Game("AVALANCHE");
        newGame.play();
    }
}
