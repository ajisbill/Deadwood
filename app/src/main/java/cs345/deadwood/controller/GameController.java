package cs345.deadwood.controller;

import cs345.deadwood.model.GameEngine;
import cs345.deadwood.model.IRole;
import cs345.deadwood.model.ISet;

public class GameController implements IController {
    private final GameEngine model;

    public GameController(GameEngine model) {
        this.model = model;
    }

    @Override
    public void clicked(IRole role) {
        // TODO: Implement
    }

    @Override
    public void clicked(ISet set) {
        // TODO: Implement
    }

    @Override
    public void clickedMove(String action) {
        // TODO: Implement
    }

}
