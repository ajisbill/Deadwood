package cs345.deadwood.view;

import cs345.deadwood.model.ISetScene;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetSceneView {

    private final JFrame board;
    private JLabel card;
    private JLabel role1;
    private JLabel shotIcon;
    private JPanel cardPanel;
    private JLabel cardLabel;

    public SetSceneView(JFrame parentContainer) {
        board = parentContainer;
    }

    public void drawSet() {

        /*
         * Create a JPanel to render things on the card.
         */
        cardPanel = new JPanel();
        cardPanel.setLocation(21, 69);
        cardPanel.setSize(205, 115); // height and width from board.xml, set name "Train Station", area element
        cardPanel.setLayout(null); // set layout to null so we can render roles on the card (x-y values in roles in cards.xml). The x-y values for roles in cards.xml are relative to the card.
        cardPanel.setOpaque(false);
        board.add(cardPanel);

//        cardPanel.addMouseListener(this); // uncomment this to list to clicks on this set


        cardLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath()));
        cardLabel.setLocation(0, 0);
        cardLabel.setSize(205, 115); // height and width from board.xml, set name "Train Station", area element
        cardPanel.add(cardLabel);


        // sample code showing how to place player dice on a role
        // Role 1 is Crusty Prospector
        role1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/dice_b1.png").getPath()));
        role1.setLocation(114, 227); // x,y values from board.xml, set name "Train Station", part Crusty Prospector
        role1.setSize(46, 46); // height and width from board.xml, set name "Train Station", part Crusty Prospector
        board.add(role1);

        // sample code showing how to place the shot icon on a take
        shotIcon = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath()));
        shotIcon.setLocation(141, 11); // x,y values from board.xml, set name "Train Station", take 1
        shotIcon.setSize(47, 47); // height and width from board.xml, set name "Train Station", take 1
        board.add(shotIcon);

    }
}
