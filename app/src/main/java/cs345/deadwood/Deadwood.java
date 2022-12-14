/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cs345.deadwood;


import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.*;
import cs345.deadwood.view.BoardView;
import cs345.deadwood.view.GameLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        numberOfPlayers = 2;
        SetParser setParser  = new SetParser();
        CardParser cardParser = new CardParser();

        List<ISet> setsList = setParser.getSets();
        List<ICard> cardsList = cardParser.getCards();
        ISet trailer = null;

        for (ISet set: setsList){
            if (set.getName().equals("Trailer")){
                trailer = set;
            }
        }
        List<String> colors = Arrays.asList("b", "c", "g", "o", "p", "r", "v", "y");
        List<Player> playerList = new ArrayList<>();
        boolean isActive = false;
        for(int i = 0; i < numberOfPlayers; i++){
            if(i == 0){
                isActive = true;
            }else{
                isActive = false;
            }
            if(numberOfPlayers == 5){
                int credits = 2;
                playerList.add(new Player(i+1, trailer, 0,credits,0, colors.get(i), 1, isActive));
            }else if(numberOfPlayers == 6){
                int credits = 4;
                playerList.add(new Player(i+1, trailer, 0,credits,0, colors.get(i), 1, isActive));
            }else if (numberOfPlayers >= 7){
                int credits = 0;
                int rank = 2;
                playerList.add(new Player(i+1, trailer, 0,credits,0, colors.get(i), rank, isActive));
            }else{
                playerList.add(new Player(i+1, trailer, 0,0,0, colors.get(i), 1, isActive));
            }

        }
        Random rand = new Random();
        int randInt = rand.nextInt(2);
        int sortMethod;
        if(randInt == 0){
            sortMethod = 0;
        }else{
            sortMethod = 1;
        }
        GameEngine model = new GameEngine(numberOfPlayers, setsList, cardsList, playerList, sortMethod);
        GameController controller = new GameController(model);
        BoardView view = new BoardView(model, controller);

        view.init();

        if(model.getSortMethod() == 0){
            System.out.println("Assigning cards randomly.");
        }else{
            System.out.println("Assigning cards by budget.");
        }
    }
}
