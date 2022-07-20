package cs345.deadwood.view;

import cs345.deadwood.model.IRole;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoleView extends JLabel implements MouseListener {
    private final IRole model;

    public RoleView(IRole model) {
        this.model = model;


        setLocation(model.getArea().getX(), model.getArea().getY());
        setSize(model.getArea().getW(), model.getArea().getH());
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameLog gameLog = GameLog.getInstance();
        gameLog.log("Role " + model.getName() + " clicked.");
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