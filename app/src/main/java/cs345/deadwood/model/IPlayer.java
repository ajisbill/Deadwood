package cs345.deadwood.model;

import cs345.deadwood.view.PlayerView;

import java.util.List;

public interface IPlayer {

    public void registerObservers(PlayerView playerView);
    public int getNumber();
    public ISet getLocation();
    public int getMoney();
    public int getCredits();
    public int getPracticeChips();
    public int getScore();
    public void setScore();
    public void setNumber(int number);
    public void setLocation(ISet set);
    public void setMoney(int money);
    public void setCredits(int Credits);
    public void setPracticeChips(int practiceChips);
    public String getDice();
    public int getRank();
    public void setRank(int rank);
    public boolean isActive();
    public void setActive(boolean isActive);



}
