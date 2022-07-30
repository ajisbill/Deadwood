package cs345.deadwood.model;

import cs345.deadwood.view.GameLog;

import java.util.List;

public class GameEngine {
    private final int numberOfPlayers;
    private final List<Player> playerList;
    private final List<ISet> setList;
    private final List<ICard> cardList;
    private int curPlayerIndex;

    private int sortMethod;

    public GameEngine(int numberOfPlayers, List<ISet> setList, List<ICard> cardList, List<Player> playerList, int sortMethod) {
        this.numberOfPlayers = numberOfPlayers;
        this.setList = setList;
        this.cardList = cardList;
        this.playerList = playerList;
        this.curPlayerIndex = 1;
        this.sortMethod = sortMethod;
    }

    public int getSortMethod() {
        return sortMethod;
    }


    public List<ISet> getSets(){
        return setList;
    }

    public List<ICard> getCards(){
        return cardList;
    }

    public List<Player> getPlayers(){
        return playerList;
    }

    public Player getNextPlayer(){
        if(curPlayerIndex == playerList.size()){
            curPlayerIndex = 1;
        }else{
            curPlayerIndex++;
        }
        return playerList.get(curPlayerIndex-1);
    }

    public void takeRole(Player player, IRole role){
        if(player.canTakeRole() && player.getRank() >= role.getLevel()  && player.getLocation().hasRole(role) && player.getRole() == null){
            player.getBlankArea().setOccupied(null);
            player.setRole(role);
            role.setOccupied(true, player);
            if(role.isOnCard()){
                player.getLocation().getSceneCard().addPlayerToCard(player);
            }
            player.setCanTakeRole(false);
            player.setTakingTurn(false);
        }else if (player.getRole() != null){
            GameLog.getInstance().log("Cannot take new role while working on role.");
        }else if(!player.getLocation().hasRole(role)){
            GameLog.getInstance().log("Cannot take role not on player's set.");
        }else if(player.getRank() < role.getLevel()){
            GameLog.getInstance().log("Cannot take role because rank is less than role level.");
        }else{
            GameLog.getInstance().log("Please click Take Role button");
        }
    }

}
