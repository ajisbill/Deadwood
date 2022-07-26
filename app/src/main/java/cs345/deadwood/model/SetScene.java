package cs345.deadwood.model;

import cs345.deadwood.view.SetSceneView;

import java.util.List;

public class SetScene extends Set implements ISetScene{

    List<TakeArea> takeAreas;

    List<IRole> roleList;

    ICard sceneCard;



    public SetScene(String name, List<ISet> neighbors, IArea area, List<IArea> blankAreas,
                    List<TakeArea> takeAreas, List<IRole> roleList, ICard sceneCard, List<String> neighborStrings) {
        super(name, neighbors, area, blankAreas, neighborStrings);
        this.takeAreas = takeAreas;
        this.roleList = roleList;
        this.sceneCard = sceneCard;
    }




    @Override
    public List<TakeArea> getTakes() {
        return this.takeAreas;
    }

    @Override
    public List<IRole> getRoles() {
        return this.roleList;
    }


}
