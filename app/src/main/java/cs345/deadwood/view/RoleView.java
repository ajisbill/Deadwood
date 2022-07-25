package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.IRole;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoleView extends JLabel implements MouseListener {
    private final IRole model;
    private final GameController controller;

    public RoleView(IRole model, GameController controller) {
        this.model = model;
        model.registerObservers(this);
        this.controller = controller;

        setLocation(model.getArea().getX(), model.getArea().getY());
        setSize(model.getArea().getW(), model.getArea().getH());

        addMouseListener(this);
    }

    public void modelUpdated(){
        if(model.isOccupied()){
            String dice = model.getPlayer().getDice();
            System.out.println("Dice icon added to roll");
            setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + dice).getPath()));
        }else{
            System.out.println("Dice icon removed from roll");
            setIcon(null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameLog gameLog = GameLog.getInstance();
        gameLog.log("Role " + model.getName() + " clicked.");
        controller.clicked(model);

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