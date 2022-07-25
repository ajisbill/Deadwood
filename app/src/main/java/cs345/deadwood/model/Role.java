package cs345.deadwood.model;

import cs345.deadwood.view.RoleView;

public class Role implements IRole {

    String name, line;
    int level;
    Area area;
    private RoleView view;
    private boolean isOccupied = false;
    private Player player;

    private boolean onCard = false;

    public Role(String name, int level, String line, Area area){
        this.name = name;
        this.level = level;
        this.line = line;
        this.area = area;
    }

    public boolean isOnCard() {
        return onCard;
    }

    public void setOnCard(boolean onCard) {
        this.onCard = onCard;
    }

    public void registerObservers(RoleView view){
        this.view = view;
    }

    public void notifyObservers(){
        view.modelUpdated();
    }

    public void setOccupied(boolean isOccupied, Player player){
        this.isOccupied = isOccupied;
        this.player = player;
        notifyObservers();
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getLine() {
        return this.line;
    }

    @Override
    public IArea getArea() {
        return this.area;
    }
}
