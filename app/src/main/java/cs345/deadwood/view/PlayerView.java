package cs345.deadwood.view;

import cs345.deadwood.model.IPlayer;
import cs345.deadwood.model.ISet;
import cs345.deadwood.model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {

    private final Player player;
    private int number;
    private ISet location;
    private int money;
    private int credits;
    private int practiceChips;
    private int score;
    private String dice;
    private int rank;
    private boolean isActive;
    private JLabel playerDice;
    private JLabel playerString;

    // remove necessary attributes
    // get string from player
    public PlayerView(Player player){
        this.player = player;
        player.registerObservers(this);

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        JLabel playerDice= new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + player.getDice()).getPath())); //todo: different dice colors for diff players
        this.playerDice = playerDice;
        this.add(playerDice);

        JLabel playerString = new JLabel(player.getPlayerString());
        this.playerString = playerString;
        if(player.isActive()){
            this.setBackground(Color.YELLOW);
        }else{
            this.setBackground(null);
        }
        this.add(playerString);
    }

    public void playerUpdated(){

        if(player.isActive()){
            this.setBackground(Color.YELLOW);
        }else{
            this.setBackground(null);
        }
        this.playerString.setText(player.getPlayerString());
    }


}
