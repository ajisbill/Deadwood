/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cs345.deadwood;

import cs345.deadwood.view.BoardView;

public class Deadwood {

    public static void main(String[] args) {
        /* Get number of players from command line arg
         * Usage: $ ./graldew run --args="2"
         */
        int numberOfPlayers = 2;  // default number of players
        if (args.length > 0) {
            try {
                numberOfPlayers = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Usage: ./gradlew run --args \"NUM\", where NUM should be an integer between 2 and 8.");
                return;
            }
            if (numberOfPlayers > 8 || numberOfPlayers < 2) {
                System.out.println("Usage: ./gradlew run --args \"NUM\", where NUM should be an integer between 2 and 8.");
                return;
            }
        }


        BoardView view = new BoardView();
        view.init();
    }
}
