package cs345.deadwood.model;

import cs345.deadwood.view.GameLog;
import cs345.deadwood.view.PlayerView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Player{


    int number;
    ISet location;
    BlankArea blankArea;
    int money;
    int credits;
    int practiceChips;
    int score;
    String color;
    int rank;
    String dice;
    PlayerView playerView;
    private boolean isActive;
    private boolean canMove;
    private boolean canTakeRole;
    private IRole role;
    private GameEngine model;
    private GameLog gameLog;
    private boolean takingTurn;
    private boolean hasMoved = false;
    String playerString;

    public Player(int number, ISet location, int money, int credits, int practiceChips, String color, int rank, boolean isActive){
        this.number = number;
        this.location = location;
        takeBlankArea();
        this.money = money;
        this.credits = credits;
        this.practiceChips = practiceChips;
        this.rank = rank;
        this.color = color;
        this.isActive = isActive;
        GameLog gameLog = GameLog.getInstance();
        this.gameLog = gameLog;
        setScore();
        setDice();
        this.playerString = ("P" + number + " " + location.getName() + ": $" + money + " C" + credits + " Pc" + practiceChips + "; S=" + score);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public String getPlayerString() {
        return playerString;
    }

    public void setPlayerString() {
        this.playerString = ("P" + number + " " + location.getName() + ": $" + money + " C" + credits + " Pc" + practiceChips + "; S=" + score);
    }

    public BlankArea getBlankArea() {
        return blankArea;
    }

    public void setBlankArea(BlankArea blankArea) {
        this.blankArea = blankArea;
    }

    public boolean isTakingTurn() {
        return takingTurn;
    }

    public void setTakingTurn(boolean takingTurn) {
        this.takingTurn = takingTurn;
    }
    public void setModel(GameEngine model){
        this.model = model;
    }

    public IRole getRole() {
        return role;
    }

    public void setRole(IRole role) {
        this.role = role;
    }

    public boolean canTakeRole() {
        return canTakeRole;
    }

    public void setCanTakeRole(boolean canTakeRole) {
        this.canTakeRole = canTakeRole;
    }

    public void registerObservers(PlayerView playerView) {
        this.playerView = playerView;
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
                this.blankArea = blank;
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
        setPlayerString();
        notifyObserver();
    }

    public ISet getLocation() {
        return location;
    }

    public void setLocation(ISet location) {
        this.location = location;
        takeBlankArea();
        setPlayerString();
        notifyObserver();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        setPlayerString();
        notifyObserver();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
        setPlayerString();
        notifyObserver();
    }

    public int getPracticeChips() {
        return practiceChips;
    }

    public void setPracticeChips(int practiceChips) {
        this.practiceChips = practiceChips;
        setPlayerString();
        notifyObserver();
    }

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score = 5 * this.rank + this.credits + this.money;
        setPlayerString();
        if (playerView != null) {
            notifyObserver();
        }
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
        notifyObserver();
    }

    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    public boolean canMove(){
        return this.canMove;
    }


}
