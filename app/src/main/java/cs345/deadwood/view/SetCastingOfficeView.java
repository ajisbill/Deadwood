package cs345.deadwood.view;

import cs345.deadwood.model.BlankArea;
import cs345.deadwood.model.IArea;
import cs345.deadwood.model.ISet;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetCastingOfficeView implements MouseListener {

    private final JFrame board;
    private ISet officeSet;
    private JPanel cardPanel;

    public SetCastingOfficeView(JFrame parentContainer, ISet oSet) {
        board = parentContainer;
        officeSet = oSet;
    }

    public void drawSet(){
        int x = this.officeSet.getArea().getX();
        int y = this.officeSet.getArea().getY();
        int h = this.officeSet.getArea().getH();
        int w = this.officeSet.getArea().getW();

        cardPanel = new JPanel();
        cardPanel.setLocation(x,y);
        cardPanel.setSize(w,h); // height and width from board.xml
        cardPanel.setLayout(null); // set layout to null so we can render roles on the card (x-y values in roles in cards.xml). The x-y values for roles in cards.xml are relative to the card.
        cardPanel.setOpaque(false);
        cardPanel.addMouseListener(this);
        board.add(cardPanel);

        for (BlankArea blank : officeSet.getBlankSpots()){
            BlankAreaView blankAreaView = new BlankAreaView(blank);
            board.add(blankAreaView);
            blank.notifyView();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameLog gameLog = GameLog.getInstance();
        gameLog.log("Set " + officeSet.getName() + " clicked.");
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
