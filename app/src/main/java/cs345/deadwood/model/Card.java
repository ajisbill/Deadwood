package cs345.deadwood.model;

import java.util.List;

public class Card implements ICard{

    public static void main(String[] args){
        CardParser parser = new CardParser();

        parser.getCards();


    }

    /**
     * @return Name of the scene card
     */
    public String getName(){
        return null;
    }

    /**
     * @return Name of the card image (png file)
     */
    public String getImageName(){
        return null;
    }

    /**
     * @return Budget of the scene
     */
    public int getBudget(){
        return 0;
    }

    /**
     * @return Number of the scene
     */
    public int getSceneNumber(){
        return 0;
    }

    /**
     * @return List of roles on the card. Null if there are no roles.
     */
    public List<IRole> getRoles(){
        return null;
    }
}
