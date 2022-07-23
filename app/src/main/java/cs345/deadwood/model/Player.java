package cs345.deadwood.model;

import cs345.deadwood.view.PlayerView;

public class Player implements IPlayer{


    int number;
    ISet location;
    int money;
    int credits;
    int practiceChips;
    int score;
    String color;
    int rank;
    String dice;
    PlayerView playerView;

    @Override
    public void registerObservers(PlayerView playerView) {
        this.playerView = playerView;
    }

    public Player(int number, ISet location, int money, int credits, int practiceChips, String color, int rank){
        this.number = number;
        this.location = location;
        takeBlankArea();
        this.money = money;
        this.credits = credits;
        this.practiceChips = practiceChips;
        this.rank = rank;
        this.color = color;
        setScore();
        setDice();
    }

    public void setColor(String Color){
        this.color = color;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return this.rank;
    }

    public void setDice() {
        this.dice = "dice_" + this.color + this.rank + ".png";
        if(playerView != null){
            notifyObserver();
        }
    }

    public String getDice(){
        return this.dice;
    }

    public void takeBlankArea(){
        for (BlankArea blank : location.getBlankSpots()){
            if(!(blank.isOccupied())){
                blank.setOccupied(this);
                break;
            }
        }
    }
    public void notifyObserver(){
        playerView.playerUpdated();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        notifyObserver();
    }

    public ISet getLocation() {
        return location;
    }

    public void setLocation(ISet location) {
        this.location = location;
        notifyObserver();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        notifyObserver();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
        notifyObserver();
    }

    public int getPracticeChips() {
        return practiceChips;
    }

    public void setPracticeChips(int practiceChips) {
        this.practiceChips = practiceChips;
        notifyObserver();
    }

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score = 5*this.rank + this.credits + this.money;
        if(playerView != null){
            notifyObserver();
        }

    }
}
