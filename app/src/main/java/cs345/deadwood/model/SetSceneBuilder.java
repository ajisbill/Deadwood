package cs345.deadwood.model;

import java.util.List;

public class SetSceneBuilder {

    private String name;
    private IArea area;
    private List<IArea> blankAreas;
    private List<String> neighborStrings;
    private List<ISet> neighbors;
    private List<IArea> takeAreas;
    private List<IRole> roleList;
    private ICard sceneCard;

    public SetSceneBuilder setName(String name){
        this.name = name;
        return this;
    }

    public SetSceneBuilder setSceneCard(ICard sceneCard){
        this.sceneCard = sceneCard;
        return this;
    }

    public SetSceneBuilder setArea(IArea area){
        this.area = area;
        return this;
    }

    public SetSceneBuilder setBlankAreas(List<IArea> areas){
        this.blankAreas = blankAreas;
        return this;
    }

    public SetSceneBuilder setNeighborStrings(List<String> neighborStrings){
        this.neighborStrings = neighborStrings;
        return this;
    }

    public SetSceneBuilder setNeighbors(List<ISet> neighbors){
        this.neighbors = neighbors;
        return this;
    }

    public SetSceneBuilder setTakeAreas(List<IArea> takeAreas){
        this.takeAreas = takeAreas;
        return this;
    }

    public SetSceneBuilder setRoleList(List<IRole> roleList) {
        this.roleList = roleList;
        return this;
    }

    public SetScene getSetScene(){
        SetScene aSet = new SetScene(name, neighbors, area, blankAreas, takeAreas, roleList, sceneCard, neighborStrings);
        return aSet;
    }






}
