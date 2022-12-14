package cs345.deadwood.view;

import cs345.deadwood.model.BlankArea;
import cs345.deadwood.model.IRole;
import cs345.deadwood.model.Player;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// remove mouseListener
public class BlankAreaView extends JLabel implements MouseListener {
    private final BlankArea model;
    private boolean occupied;

    public BlankAreaView(BlankArea blankArea) {
        this.model = blankArea;
        model.registerObservers(this);

        setLocation(model.getX(), model.getY());
        setSize(model.getW(), model.getH());
        addMouseListener(this);
    }

    public void areaUpdated(){
        if(model.isOccupied()){
            String dice = model.getPlayer().getDice();
            setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + dice).getPath()));
        }else{
            setIcon(null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        GameLog gameLog = GameLog.getInstance();
//        gameLog.log("Blank Area clicked.");

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
