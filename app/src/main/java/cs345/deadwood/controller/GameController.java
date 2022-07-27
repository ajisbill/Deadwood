package cs345.deadwood.controller;

import cs345.deadwood.model.*;
import cs345.deadwood.view.GameLog;

public class GameController implements IController {
    private final GameEngine model;
    private Player activePlayer;

    public GameController(GameEngine model) {
        this.model = model;
        for(Player player : model.getPlayers()){
            if(player.isActive()){
                this.activePlayer = player;
                break;
            }
        }
    }

    @Override
    public void clicked(IRole role) {
        // role.assign(activePlayer);
        //if Take Role button clicked and player rank >= role level, draw player dice on role and set player role
        if(activePlayer.canTakeRole() && activePlayer.getRank() >= role.getLevel() && activePlayer.getLocation().isCardActive() == true){
            activePlayer.takeRole(role);
            activePlayer.setCanTakeRole(false);
        }else if(activePlayer.getLocation().isCardActive() == false){
            GameLog.getInstance().log("Cannot take role because scene is not active.");
        }else if(activePlayer.getRank() < role.getLevel()){
            GameLog.getInstance().log("Cannot take role rank is less than role level.");
        }
    }

    @Override
    public void clicked(ISet set) {
        //if move button clicked and player not working, allow them to move to set clicked on
        if(activePlayer.canMove() && !activePlayer.isWorkingOffCard() && !activePlayer.isWorkingOffCard()){
            activePlayer.move(set);
            activePlayer.setCanMove(false);
        }
    }

    @Override
    public void clicked(Button button) {
        //move player to the next location that is clicked
        if(button.getLabel().equals("Move")){
            activePlayer.setCanMove(true);
        }else if(button.getLabel().equals("Take Role")){
            activePlayer.setCanTakeRole(true);
        }else if(button.getLabel().equals("Act")){
            activePlayer.act();
        }
    }

}
