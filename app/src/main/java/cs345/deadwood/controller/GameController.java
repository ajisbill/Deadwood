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
        model.takeRole(activePlayer, role);

    }

    @Override
    public void clicked(ISet set) {
        model.move(activePlayer, set);
    }

    @Override
    public void clicked(Button button) {
        if(button.getLabel().equals("Move") && activePlayer.isTakingTurn()){
            activePlayer.setCanMove(true);
        }else if(button.getLabel().equals("Take Role") && activePlayer.isTakingTurn()){
            activePlayer.setCanTakeRole(true);
        }else if(button.getLabel().equals("Act") && activePlayer.isTakingTurn()){
            model.act(activePlayer);
        }else if(button.getLabel().equals("End Turn")){
            setActivePlayer();
        }else if(button.getLabel().equals("Rehearse") && activePlayer.isTakingTurn()){
            model.rehearse(activePlayer);
        }else if(!activePlayer.isTakingTurn()){
            GameLog.getInstance().log("Please end turn.");
        }
    }

    public void setActivePlayer(){
        activePlayer.setHasMoved(false);
        activePlayer.setActive(false);
        activePlayer = model.getNextPlayer();
        activePlayer.setActive(true);
        activePlayer.setTakingTurn(true);
    }

}
