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

    //remove redundant attributes
    private boolean workingOnCard = false;
    private boolean workingOffCard = false;
    private GameEngine model;
    private GameLog gameLog;

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

    public void takeRole(IRole role){
        if(this.role != null){
            this.role.setOccupied(false, null);
            if(this.role.isOnCard()){
                location.getSceneCard().removePlayerFromCard(this);
            }
        }
        this.blankArea.setOccupied(null);
        this.setRole(role);
        role.setOccupied(true, this);
        if(role.isOnCard()){
            location.getSceneCard().addPlayerToCard(this);
        }
    }

    public void move(ISet newSet){
        if(this.role != null){
            this.role.setOccupied(false, null);
        }
        if(newSet.getSceneCard() == null && !newSet.getName().equals("Trailer") && !newSet.getName().equals("Office")){
            newSet.setCardActive(true);
        }
        this.blankArea.setOccupied(null);
        this.setLocation(newSet);
    }

    public void act(){
        Random rand = new Random();
        int randNum = ThreadLocalRandom.current().nextInt(1,7);
        GameLog gameLog = GameLog.getInstance();
        gameLog.log("Player" + number + " rolled a " + randNum +".");
        //success
        if(randNum >= location.getSceneCard().getBudget()){
            gameLog.log("Acting is successful!");
            if(role.isOnCard()){
                gameLog.log("Player" + number + " receives 2 credits.");
                setCredits(credits + 2);
                location.getTakes().get(0).setActive(false);
                location.getTakes().remove(0);
                if(location.getTakes().size() == 0){
                    location.wrapScene();
                }
            }else{
                gameLog.log("Player" + number + " receives 1 credit and 1 dollar.");
                setCredits(credits + 1);
                setMoney(money +1 );
                location.getTakes().get(0).setActive(false);
                location.getTakes().remove(0);
                if(location.getTakes().size() == 0){
                    location.wrapScene();
                }
            }
        }else{
            gameLog.log("Acting is unsuccessful!");
            if(role.isOnCard()){
                gameLog.log("Player" + number + " receives nothing.");
            }else {
                gameLog.log("Player" + number + " receives 1 dollar.");
                setMoney(money + 1);
            }
        }
    }


    public boolean isWorkingOnCard() {
        return workingOnCard;
    }

    public void setWorkingOnCard(boolean workingOnCard) {
        this.workingOnCard = workingOnCard;
    }

    public boolean isWorkingOffCard() {
        return workingOffCard;
    }

    public void setWorkingOffCard(boolean workingOffCard) {
        this.workingOffCard = workingOffCard;
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
        notifyObserver();
    }

    public ISet getLocation() {
        return location;
    }

    public void setLocation(ISet location) {
        this.location = location;
        takeBlankArea();
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
        this.score = 5 * this.rank + this.credits + this.money;
        if (playerView != null) {
            notifyObserver();
        }
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    public boolean canMove(){
        return this.canMove;
    }


}
