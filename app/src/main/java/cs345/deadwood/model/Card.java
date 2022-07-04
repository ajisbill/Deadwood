package cs345.deadwood.model;

import java.util.List;

public class Card implements ICard{

    String sceneName, imageName;
    int budget, sceneNumber;

    List<IRole> roleList;

    public Card(String scName, String imgName, int budg, int scNum, List<IRole> roles){
        this.sceneName = scName;
        this.imageName = imgName;
        this.budget = budg;
        this.sceneNumber = scNum;
        this.roleList = roles;

    }
//    public static void main(String[] args){
//        CardParser parser = new CardParser();
//
//        List<ICard> test = parser.getCards();
//
//        for (int i = 0; i < test.size(); i++) {
//            System.out.println(test.get(i).getName());
//            System.out.println(test.get(i).getImageName());
//            System.out.println(test.get(i).getBudget());
//            System.out.println(test.get(i).getSceneNumber());
//            System.out.println(test.get(i).getRoles());
//        }
//
//    }

    /**
     * @return Name of the scene card
     */
    public String getName(){
        return this.sceneName;
    }

    /**
     * @return Name of the card image (png file)
     */
    public String getImageName(){
        return this.imageName;
    }

    /**
     * @return Budget of the scene
     */
    public int getBudget(){
        return this.budget;
    }

    /**
     * @return Number of the scene
     */
    public int getSceneNumber(){
        return this.sceneNumber;
    }

    /**
     * @return List of roles on the card. Null if there are no roles.
     */
    public List<IRole> getRoles(){
        return this.roleList;
    }
}
