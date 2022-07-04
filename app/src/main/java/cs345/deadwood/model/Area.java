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
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getH() {
        return this.h;
    }

    @Override
    public int getW() {
        return this.w;
    }
}
