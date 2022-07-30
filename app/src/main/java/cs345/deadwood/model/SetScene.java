package cs345.deadwood.model;

import cs345.deadwood.view.GameLog;
import cs345.deadwood.view.SetSceneView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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


    public boolean hasRole(IRole newRole){
        for (IRole role : roleList){
            if(role.getName().equals(newRole.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TakeArea> getTakes() {
        return this.takeAreas;
    }

    @Override
    public List<IRole> getRoles() {
        return this.roleList;
    }

    public List<Player> getPlayersOnCard(){
        return getSceneCard().getPlayersOnCard();

    }

    public List<Player> getPlayersOffCard(){
        List<Player> playersOffCard = new ArrayList<>();
        for(IRole role : getRoles()){
            if (role.getPlayer() != null){
                playersOffCard.add(role.getPlayer());
            }
        }
        return playersOffCard;
    }

    public void wrapScene(){
        List<Player> playersOnCard = getPlayersOnCard();
        List<Player> playersOffCard = getPlayersOffCard();
        GameLog.getInstance().log("Scene Wrapped");

        this.setCardActive(false);

        int randNum =0 ;
        ICard card = this.getSceneCard();
        int budget = card.getBudget();
        int numOnCardRolls = card.getRoles().size();
        int[] diceVals = new int[budget];
        int[] payout = new int[numOnCardRolls];

        // roll number of dice equal to budget and sort them
        for (int i = 0; i < budget; i++){
            randNum = ThreadLocalRandom.current().nextInt(1,7);
            diceVals[i] = randNum;
        }
        Arrays.sort(diceVals);
        int ind = 0;

        for(int i = budget - 1; i>=0; i--){
            payout[ind] += diceVals[i];
            ind++;
            if(ind == numOnCardRolls){
                ind = 0;
            }
        }

        GameLog.getInstance().log("Dice rolled are: " + Arrays.toString(diceVals));
        GameLog.getInstance().log(Arrays.toString(payout));

        for(Player player : playersOnCard){
            if(player.getRole().getPriority() == 1){
                int bonus = payout[0];
                player.setMoney(player.getMoney() + bonus);
                GameLog.getInstance().log("Player" + player.getNumber() + " received on card bonus of $" + bonus);
                player.setPracticeChips(0);
                player.setLocation(this);
                player.getRole().setOccupied(false,null);
                player.setRole(null);
            }else if(player.getRole().getPriority() == 2){
                int bonus = payout[1];
                player.setMoney(player.getMoney() + bonus);
                GameLog.getInstance().log("Player" + player.getNumber() + " received on card bonus of $" + bonus);
                player.setPracticeChips(0);
                player.setLocation(this);
                player.getRole().setOccupied(false,null);
                player.setRole(null);
            }else if(player.getRole().getPriority() == 3){
                int bonus = payout[2];
                player.setMoney(player.getMoney() + bonus);
                GameLog.getInstance().log("Player" + player.getNumber() + " received on card bonus of $" + bonus);
                player.setPracticeChips(0);
                player.setLocation(this);
                player.getRole().setOccupied(false,null);
                player.setRole(null);
            }
        }

        for(Player player : playersOffCard){
            int bonus = player.getRole().getLevel();
            player.setMoney(player.getMoney() + bonus);
            GameLog.getInstance().log("Player" + player.getNumber() + " received off card bonus of $" + bonus);
            player.setPracticeChips(0);
        }




    }


}
