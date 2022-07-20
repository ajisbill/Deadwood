package cs345.deadwood.view;

import cs345.deadwood.model.ISet;

import javax.swing.*;

public class SetTrailerView {

    private final JFrame board;
    private ISet trailerSet;
    private JPanel cardPanel;

    public SetTrailerView(JFrame parentContainer, ISet tSet) {
        board = parentContainer;
        trailerSet = tSet;

    }

    public void drawSet(){
        int x = this.trailerSet.getArea().getX();
        int y = this.trailerSet.getArea().getY();
        int h = this.trailerSet.getArea().getH();
        int w = this.trailerSet.getArea().getW();

        cardPanel = new JPanel();
        cardPanel.setLocation(x,y);
        cardPanel.setSize(w,h); // height and width from board.xml
        cardPanel.setLayout(null); // set layout to null so we can render roles on the card (x-y values in roles in cards.xml). The x-y values for roles in cards.xml are relative to the card.
        cardPanel.setOpaque(false);
        board.add(cardPanel);
    }

}
