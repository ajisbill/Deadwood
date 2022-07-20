package cs345.deadwood.view;

import cs345.deadwood.model.ISet;

import javax.swing.*;

public class SetCastingOfficeView {

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
        board.add(cardPanel);
    }
}
