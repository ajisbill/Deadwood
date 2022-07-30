package cs345.deadwood.controller;

import cs345.deadwood.model.*;
import cs345.deadwood.view.GameLog;

import java.util.List;

public class GameController implements IController {
    private final GameEngine model;
    private Player activePlayer;
    private List<Player> players;

    public GameController(GameEngine model) {
        this.model = model;
        this.players = model.getPlayers();
        for(Player player : players){
            if(player.isActive()){
                this.activePlayer = player;
                activePlayer.setTakingTurn(true);
                break;
            }
        }
    }

    @Override
    public void clicked(IRole role) {
        // role.assign(activePlayer);
        //if Take Role button clicked and player rank >= role level, draw player dice on role and set player role
        model.takeRole(activePlayer, role);
//        if(activePlayer.canTakeRole() && activePlayer.getRank() >= role.getLevel() && activePlayer.getLocation().isCardActive() == true){
//            activePlayer.takeRole(role);
//            activePlayer.setCanTakeRole(false);
//            activePlayer.setTakingTurn(false);
//        }else if(activePlayer.getLocation().isCardActive() == false){
//            GameLog.getInstance().log("Cannot take role because scene is not active.");
//        }else if(activePlayer.getRank() < role.getLevel()){
//            GameLog.getInstance().log("Cannot take role because rank is less than role level.");
//        }
    }

    @Override
    public void clicked(ISet set) {
        activePlayer.move(set);
    }

    @Override
    public void clicked(Button button) {
        //move player to the next location that is clicked
        if(button.getLabel().equals("Move") && activePlayer.isTakingTurn()){
            activePlayer.setCanMove(true);
        }else if(button.getLabel().equals("Take Role") && activePlayer.isTakingTurn()){
            activePlayer.setCanTakeRole(true);
        }else if(button.getLabel().equals("Act") && activePlayer.isTakingTurn()){
            activePlayer.act();
            activePlayer.setTakingTurn(false);
        }else if(button.getLabel().equals("End Turn")){
            activePlayer.setActive(false);
            activePlayer = model.getNextPlayer();
            activePlayer.setActive(true);
            activePlayer.setTakingTurn(true);
        }else if(button.getLabel().equals("Rehearse") && activePlayer.isTakingTurn()){
            boolean succesful = activePlayer.rehearse();
            if(succesful){
                activePlayer.setTakingTurn(false);
            }
        }else if(!activePlayer.isTakingTurn()){
            GameLog.getInstance().log("Please end turn.");
        }
    }

//    public void setActivePlayer(){
//        activePlayer.setActive(false);
//        activePlayer = model.getNextPlayer();
//        activePlayer.setActive();
//    }

}
