package cs345.deadwood.model;

import cs345.deadwood.view.GameLog;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        if(player.canTakeRole() && player.getRank() >= role.getLevel()  && player.getLocation().hasRole(role) && player.getRole() == null && player.getLocation().isCardActive()){
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
        }else if(!player.getLocation().isCardActive()){
            GameLog.getInstance().log("Cannot take role on inactive set.");
        }
        else{
            GameLog.getInstance().log("Please click Take Role button");
        }
    }

    public void move(Player player, ISet newSet){
        if(player.canMove() && player.getRole() == null && player.getLocation().isAdjacent(newSet) && !player.hasMoved()){
            if(newSet.getSceneCard() == null && !newSet.getName().equals("Trailer") && !newSet.getName().equals("Office")){
                newSet.setCardActive(true);
            }
            player.getBlankArea().setOccupied(null);
            player.setLocation(newSet);
            player.setCanMove(false);
            player.setHasMoved(true);
        }else if(player.getRole() != null){
            GameLog.getInstance().log("Cannot move to new set while working on role.");
        }else if(!player.getLocation().isAdjacent(newSet)){
            GameLog.getInstance().log("Cannot move to non-adjacent set");
        }else if(!player.canMove()){
            GameLog.getInstance().log("Please click move button to move to new set");
        }else if(player.hasMoved()){
            GameLog.getInstance().log("Player cannot move twice in one turn.");
        }

    }

    public void rehearse(Player player){
        if(player.getRole() != null){
            player.setPracticeChips(player.getPracticeChips() + 1);
            GameLog.getInstance().log("Player" + player.getNumber() + "received 1 practice chip");
            player.setTakingTurn(false);
        }else if(player.getRole() == null){
            GameLog.getInstance().log("Cannot rehearse if not on role.");
        }
    }

    public void act(Player player){
        if(player.getRole() != null){
            Random rand = new Random();
            int randNum = ThreadLocalRandom.current().nextInt(1,7);
            GameLog gameLog = GameLog.getInstance();
            gameLog.log("Player" + player.getNumber() + " rolled a " + randNum +".");
            //success
            if(randNum + player.getPracticeChips() >= player.getLocation().getSceneCard().getBudget()){
                gameLog.log("Acting is successful!");
                if(player.getRole().isOnCard()){
                    gameLog.log("Player" + player.getNumber() + " receives 2 credits.");
                    player.setCredits(player.getCredits() + 2);
                    player.getLocation().getTakes().get(0).setActive(false);
                    player.getLocation().getTakes().remove(0);
                    if(player.getLocation().getTakes().size() == 0){
                        player.getLocation().wrapScene();
                    }
                }else{
                    gameLog.log("Player" + player.getNumber() + " receives 1 credit and 1 dollar.");
                    player.setCredits(player.getCredits() + 1);
                    player.setMoney(player.getMoney() +1 );
                    player.getLocation().getTakes().get(0).setActive(false);
                    player.getLocation().getTakes().remove(0);
                    if(player.getLocation().getTakes().size() == 0){
                        player.getLocation().wrapScene();
                    }
                }
            }else{
                gameLog.log("Acting is unsuccessful!");
                if(player.getRole().isOnCard()){
                    gameLog.log("Player" + player.getNumber() + " receives nothing.");
                }else {
                    gameLog.log("Player" + player.getNumber() + " receives 1 dollar.");
                    player.setMoney(player.getMoney() + 1);
                }
            }
            player.setTakingTurn(false);
        }else if (player.getRole() == null){
            GameLog.getInstance().log("Cannot act if not on role.");
        }

    }

}
