package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.Button;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonView extends JButton implements MouseListener {

    private String label;
    private Button buttonModel;
    private GameController controller;

    public ButtonView(Button buttonModel, String label, GameController controller){
        this.buttonModel = buttonModel;
        this.label = label;
        this.controller = controller;
        setText(label);
        addMouseListener(this);
        buttonModel.registerObservers(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(this.label + " button clicked");
        controller.clicked(this.buttonModel);
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
