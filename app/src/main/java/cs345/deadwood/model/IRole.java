package cs345.deadwood.model;

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
}
