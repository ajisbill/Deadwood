package cs345.deadwood.view;

import cs345.deadwood.model.IPlayer;
import cs345.deadwood.model.ISet;
import cs345.deadwood.model.Player;

import javax.swing.*;

public class PlayerView extends JPanel {

    private final IPlayer player;
    private int number;
    private ISet location;
    private int money;
    private int credits;
    private int practiceChips;
    private int score;
    private JLabel playerDice;
    private JLabel playerString;

    //todo: active player has yellow highlight

    public PlayerView(IPlayer player, String dice){
        this.player = player;
        player.registerObservers(this);
        this.playerUpdated();

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JLabel playerDice= new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + dice).getPath())); //todo: different dice colors for diff players
        this.playerDice = playerDice;
        this.add(playerDice);

        JLabel playerString = new JLabel("P" + number + " " + location.getName() + ": $" + money + " C" + credits + " Pc" + practiceChips + "; S=" + score);
        this.playerString = playerString;
        this.add(playerString);
    }

    public void playerUpdated(){
        this.number = player.getNumber();
        this.location = player.getLocation();
        this.money = player.getMoney();
        this.credits = player.getCredits();
        this.practiceChips = player.getPracticeChips();
        this.score = player.getScore();
    }

    public void drawDice(int x, int y, int h, int w){
        playerDice.setLocation(x,y);
        playerDice.setSize(46,46); //todo: variable size?
    }

}
