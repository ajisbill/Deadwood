package cs345.deadwood.model;

import cs345.deadwood.view.RoleView;

public interface IRole {

    /**
     * @return Name of the role
     */
    String getName();

    /**
     * @return Level of the role
     */
    int getLevel();

    /**
     * @return Line of the role
     */
    String getLine();

    /**
     * @return Area of the role
     */
    IArea getArea();

    void registerObservers(RoleView view);
    public void setOccupied(boolean isOccupied, Player player);
    public boolean isOccupied();
    public Player getPlayer();
    public boolean isOnCard();
    public void setOnCard(boolean onCard);
    public RoleView getView();
}
