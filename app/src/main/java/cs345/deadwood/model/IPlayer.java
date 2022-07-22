package cs345.deadwood.model;

import cs345.deadwood.view.PlayerView;

public interface IPlayer {

    public void registerObservers(PlayerView playerView);
    public int getNumber();
    public ISet getLocation();
    public int getMoney();
    public int getCredits();
    public int getPracticeChips();
    public int getScore();

}
