package cs345.deadwood.model;

public class Role implements IRole {

    String name, line;
    int level;
    Area area;

    public Role(String name, int level, String line, Area area){
        this.name = name;
        this.level = level;
        this.line = line;
        this.area = area;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getLine() {
        return this.line;
    }

    @Override
    public IArea getArea() {
        return this.area;
    }
}
