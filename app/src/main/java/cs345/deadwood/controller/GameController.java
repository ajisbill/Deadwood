package cs345.deadwood.controller;

import cs345.deadwood.model.*;

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
        // TODO: Implement
    }

    @Override
    public void clicked(ISet set) {
        if(activePlayer.canMove()){
            activePlayer.move(set);
            activePlayer.setCanMove(false);
        }
    }

    @Override
    public void clicked(Button button) {
        //move player to the next location that is clicked
        activePlayer.setCanMove(true);
    }

}
