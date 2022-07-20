package cs345.deadwood.model;

import cs345.deadwood.view.PlayerView;

public class Player implements IPlayer{


    String number;
    ISet location;
    int money;
    int credits;
    int practiceChips;
    int score;
    PlayerView playerView;

    @Override
    public void registerObservers(PlayerView playerView) {
        this.playerView = playerView;
    }


}
