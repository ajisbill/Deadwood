package cs345.deadwood.model;

import java.util.ArrayList;
import java.util.List;

public class Set implements ISetScene{

    String name;
    List<ISet> neighbors;
    Area area;
    List<IArea> blankAreas;
    ICard sceneCard;
    List<IRole> setRoles;
    List<IArea> takeAreas;


    public Set(String name, List<ISet> neighbors, Area area, List<IArea> blankAreas, ICard sceneCard, List<IRole> setRoles, List<IArea> takeAreas){
        this.name = name;
        this.neighbors = neighbors;
        this.area = area;
        this.blankAreas = blankAreas;
        this.sceneCard = sceneCard;
        this.setRoles = setRoles;
        this.takeAreas = takeAreas;
    }

    public static void main(String[] args){
        SetParser parser = new SetParser();

        List<ISet> test = parser.getSets();

        for (int i = 0; i < test.size(); i++) {
            System.out.println("Set Name: " + test.get(i).getName());
            System.out.println("Area: x: " + test.get(i).getArea().getX() + ", y: "
                                + test.get(i).getArea().getY() + ", h: "
                                + test.get(i).getArea().getH() + ", w: "
                                + test.get(i).getArea().getW());
            List<IArea> blanks = test.get(i).getBlankAreas();
            for(int j =0; j < blanks.size(); j++){
                System.out.println("Blank Area: x: " + blanks.get(j).getX() + ", y: "
                        + blanks.get(j).getY() + ", h: "
                        + blanks.get(j).getH() + ", w: "
                        + blanks.get(j).getW());
            }
            if (i < test.size() -2) {
                List<IArea> takes = test.get(i).getTakes();
                for (int j = 0; j < takes.size(); j++) {
                    System.out.println("Take Area: x: " + takes.get(j).getX() + ", y: "
                            + takes.get(j).getY() + ", h: "
                            + takes.get(j).getH() + ", w: "
                            + takes.get(j).getW());
                }
            }
        }

    }
    /**
     * @return Name of the set
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return List of neighbors of this set
     */
    public List<ISet> getNeighbors(){
        return this.neighbors;
    }

    /**
     * @return Area of this set
     */
    public IArea getArea(){
        return this.area;
    }

    /**
     * @return List of blank areas on this set.
     */
    public List<IArea> getBlankAreas(){
        return this.blankAreas;
    }

    @Override
    public List<IArea> getTakes() {
        return this.takeAreas;
    }

    @Override
    public List<IRole> getRoles() {
        return this.setRoles;
    }

    @Override
    public ICard getSceneCard() {
        return this.sceneCard;
    }
}
