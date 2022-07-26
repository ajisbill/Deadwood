package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.controller.IController;
import cs345.deadwood.model.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;

public class SetSceneView implements MouseListener {

    private final JFrame board;
    private JLabel card;
    private ISetScene setScene;
    private JLabel role1;
    private JLabel shotIcon;
    private JPanel cardPanel;
    private JLabel cardLabel;
    private GameController controller;
    private GameEngine model;
    private List<ICard> cards;
    private ICard randCard;
    private int x;
    private int y;
    private int h;
    private int w;


    public SetSceneView(JFrame parentContainer, ISetScene aSet, GameController controller, GameEngine model) {
        board = parentContainer;
        this.setScene = aSet;
        setScene.registerObservers(this);
        this.controller = controller;
        this.model = model;
        this.cards = model.getCards();
    }

    public void drawSet() {

        /*
         * Create a JPanel to render things on the card.
         */

        x = this.setScene.getArea().getX();
        y = this.setScene.getArea().getY();
        h = this.setScene.getArea().getH();
        w = this.setScene.getArea().getW();

        cardPanel = new JPanel();
        cardPanel.setLocation(x,y);
        cardPanel.setSize(w,h); // height and width from board.xml
        cardPanel.setLayout(null); // set layout to null so we can render roles on the card (x-y values in roles in cards.xml). The x-y values for roles in cards.xml are relative to the card.
        cardPanel.setOpaque(false);
        cardPanel.addMouseListener(this);
        board.add(cardPanel);

//        cardPanel.addMouseListener(this); // uncomment this to list to clicks on this set
        cardLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath()));


        //System.out.println(this.setScene.getName() + ", x: " + x + ", y: " + y + ", h: "+  h + ", w: " + w);
        cardLabel.setLocation(0, 0);
        cardLabel.setSize(w, h); // height and width from board.xml
        cardPanel.add(cardLabel);



        for (IRole role : setScene.getRoles()) {
            RoleView rView = new RoleView(role, controller);
            board.add(rView);
        }

        for (IArea area : setScene.getTakes()){
            shotIcon = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath()));
            int x1 = area.getX();
            int y1 = area.getY();
            int h1 = area.getH();
            int w1 = area.getW();
            shotIcon.setLocation(x1, y1); // x,y values from board.xml, set name "Train Station", take 1
            shotIcon.setSize(w1, h1); // height and width from board.xml, set name "Train Station", take 1
            board.add(shotIcon);
        }

        for (BlankArea blank : setScene.getBlankSpots()){
            BlankAreaView blankAreaView = new BlankAreaView(blank);
            board.add(blankAreaView);
            blank.notifyView();
        }


        // sample code showing how to place player dice on a role
        // Role 1 is Crusty Prospector
//        role1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/dice_b1.png").getPath()));
//        role1.setLocation(114, 227); // x,y values from board.xml, set name "Train Station", part Crusty Prospector
//        role1.setSize(46, 46); // height and width from board.xml, set name "Train Station", part Crusty Prospector
//        board.add(role1);

        // sample code showing how to place the shot icon on a take
//        shotIcon = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath()));
//        shotIcon.setLocation(141, 11); // x,y values from board.xml, set name "Train Station", take 1
//        shotIcon.setSize(47, 47); // height and width from board.xml, set name "Train Station", take 1
//        board.add(shotIcon);

    }

    public void modelUpdated(){
        if(setScene.isEntered() == true){

            cardPanel.remove(cardLabel);

            Random rand = new Random();
            int randInt = rand.nextInt(cards.size());
            randCard = cards.get(randInt);
            cards.remove(randInt);

            for(IRole role : randCard.getRoles()){
                role.setOnCard(true);
                RoleView rView = new RoleView(role, controller);
                cardPanel.add(rView);
            }
            System.out.println("setScene entered");
            setScene.setSceneCard(randCard);
            cardPanel.add(cardLabel);
            cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/"+ randCard.getImageName()).getPath()));
            board.revalidate();
            // remove cardLabel from panel
            // add roles
            // add cardLabel with icon

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameLog gameLog = GameLog.getInstance();
        gameLog.log("Set " + setScene.getName() + " clicked.");
        controller.clicked(this.setScene);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
