/**
 * The main program for running Mancala.
 * @author Ryan Campbell
 */
public class Main {
    /** Usage: java Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        if (args.length == 0) {
            Game newGame = new Game("CAPTURE");
            newGame.play();
        } else if (args.length == 1) {
            Game newGame = new Game(args[0]);
            newGame.play();
        } else if (args.length == 3) {
            Game newGame = new Game(args[0], args[1], args[2]);
            newGame.play();
        } else if (args.length == 5) {
            Game newGame = new Game(args[0], args[1], args[2], args[3], args[4]);
            newGame.play();
        } else {
            System.out.println("\u001B[33mInvalid Arguments\u001B[0m");
            System.exit(1);
        }
    }
}
