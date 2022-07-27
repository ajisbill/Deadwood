package cs345.deadwood.model;

import java.util.List;

/**
 * Interface that every scene card (from cards.xml) should implement.
 */
public interface ICard {

    /**
     * @return Name of the scene card
     */
    String getName();

    /**
     * @return Name of the card image (png file)
     */
    String getImageName();

    /**
     * @return Budget of the scene
     */
    int getBudget();

    /**
     * @return Number of the scene
     */
    int getSceneNumber();

    /**
     * @return List of roles on the card. Null if there are no roles.
     */
    List<IRole> getRoles();

    public List<Player> getPlayersOnCard();

    public void addPlayerToCard(Player player);

    public void removePlayerFromCard(Player player);
}
