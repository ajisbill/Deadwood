package cs345.deadwood.view;

import cs345.deadwood.model.TakeArea;

import javax.swing.*;

public class TakeAreaView extends JLabel {

    private TakeArea takeArea;

    public TakeAreaView(TakeArea takeArea){
        this.takeArea = takeArea;
        takeArea.registerObservers(this);
        setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath()));
    }

    public void modelUpdated(){
        if(takeArea.isActive()){
            setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath()));
        }else{
            System.out.println("made it");
            setIcon(null);
        }
    }


}
