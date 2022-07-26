package cs345.deadwood.model;

import cs345.deadwood.view.TakeAreaView;

public class TakeArea extends Area{

    private TakeAreaView takeAreaView;
    private boolean isActive = true;


    public TakeArea(int x, int y, int h, int w, boolean isActive) {
        super(x, y, h, w);
        this.isActive = isActive;
    }

    public void registerObservers(TakeAreaView takeAreaView){
        this.takeAreaView = takeAreaView;
    }

    public void notifyView(){
        takeAreaView.modelUpdated();
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
        notifyView();
    }

    public boolean isActive(){
        return isActive;
    }

}
