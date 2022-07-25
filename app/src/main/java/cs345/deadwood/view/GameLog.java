package cs345.deadwood.view;

import cs345.deadwood.model.IGameLog;

public class GameLog implements IGameLog {

    private static GameLog uniqueInstance = new GameLog();
    private String message;
    private BoardView board;
    private GameLog() {
    }

    public static GameLog getInstance() {
        return uniqueInstance;
    }

    public void registerObservers(BoardView board){
        this.board = board;
    }

    public void log(String message){
        this.message = message;
        notifyBoard();
    }

    public String getMessage(){
        return this.message;
    }

    public void notifyBoard(){
        board.logUpdated();
    }


}
