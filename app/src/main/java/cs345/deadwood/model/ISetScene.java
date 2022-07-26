package cs345.deadwood.model;

import cs345.deadwood.view.SetSceneView;

import java.util.List;

/**
 * Interface that every scene set should implement.
 */
public interface ISetScene extends ISet {

    /**
     * @return List of takes for this set.
     */
    List<TakeArea> getTakes();

    /**
     * @return List of roles on this set. These would be off-card roles.
     */
    List<IRole> getRoles();

    /**
     * @return Return the scene card for this set or null if no card has been assigned yet.
     */
    ICard getSceneCard();

    void registerObservers(SetSceneView setSceneView);

    public void setSceneCard(ICard card);
    public void notifyObservers();

}
