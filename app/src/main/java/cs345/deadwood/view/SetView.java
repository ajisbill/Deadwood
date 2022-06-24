package cs345.deadwood.view;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetView {

    private final JFrame board;
    private JLabel card;
    private JLabel role1;
    private JLabel shotIcon;

    public SetView(JFrame parentContainer) {
        board = parentContainer;
    }

    public void drawSet() {

        card = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath()));
        card.setLocation(21, 69); // x,y values from board.xml, set name "Train Station", area element
        card.setSize(205, 115); // height and width from board.xml, set name "Train Station", area element
        board.add(card);

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
