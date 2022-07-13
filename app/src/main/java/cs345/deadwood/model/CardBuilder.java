package cs345.deadwood.model;

import java.util.ArrayList;
import java.util.List;

public class CardBuilder {

    String sceneName, imageName;
    int budget, sceneNumber;
    List<IRole> roleList = new ArrayList<>();

    public CardBuilder addSceneName(String sceneName){
        this.sceneName = sceneName;
        return this;
    }

    public CardBuilder addImageName(String imageName){
        this.imageName = imageName;
        return this;
    }

    public CardBuilder addBudget(int budget){
        this.budget = budget;
        return this;
    }

    public CardBuilder addSceneNumber(int sceneNumber){
        this.sceneNumber = sceneNumber;
        return this;
    }

    public CardBuilder addRole(IRole role){
        roleList.add(role);
        return this;
    }

    public Card getCard(){
        Card c = new Card(sceneName, imageName, budget, sceneNumber, roleList);
        return c;
    }
}
