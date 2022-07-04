package cs345.deadwood.model;

public class Area implements IArea{
    int x, y, h, w;

    public Area(int x, int y, int h, int w){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }
    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getH() {
        return 0;
    }

    @Override
    public int getW() {
        return 0;
    }
}
