package cs345.deadwood.model;

import cs345.deadwood.view.BlankAreaView;
import cs345.deadwood.view.PlayerView;

public class BlankArea {
    Player player;
    BlankAreaView view;
    int x;
    int y;
    int h;
    int w;

    public BlankArea(Player player, int x, int y, int h, int w){
        this.player = player;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w=w;
    }

    public void registerObservers(BlankAreaView view){
        this.view = view;
    }

    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }


    public int getH() {
        return this.h;
    }


    public int getW() {
        return this.w;
    }

    public void setOccupied(Player player){
        this.player = player;
        notifyView();
    }

    public void notifyView(){
        view.areaUpdated();
    }

    public boolean isOccupied(){
        if(player != null){
            return true;
        }else{
            return false;
        }
    }

    public Player getPlayer(){
        return this.player;
    }

}
