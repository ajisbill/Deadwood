package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.GameEngine;
import cs345.deadwood.model.ICard;
import cs345.deadwood.model.IRole;

import javax.swing.*;

public class CardView extends JLabel {

    private ICard card;
    private GameController controller;
    private JFrame board;
    private JPanel cardPanel;

    public CardView(ICard card, GameController controller, JFrame board, JPanel cardPanel){
        this.card = card;
        this.controller = controller;
        this.board = board;
        this.cardPanel = cardPanel;
        setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/"+ card.getImageName()).getPath()));

        for(IRole role : card.getRoles()){
            role.setOnCard(true);
            RoleView rView = new RoleView(role, controller);
            cardPanel.add(rView);
        }


    }

}
