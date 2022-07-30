package cs345.deadwood.model;

import java.util.ArrayList;
import java.util.List;

public class SetBuilder {
    private String name;

    private IArea area;

    private List<IArea> blankAreas = new ArrayList<>();
    private List<String> neighborStrings = new ArrayList<>();
    private List<ISet> neighbors = new ArrayList<>();

    public SetBuilder setName(String name){
        this.name = name;
        return this;
    }

    public SetBuilder setArea(IArea area){
        this.area = area;
        return this;
    }

    public SetBuilder addBlankArea(IArea blankArea){
        blankAreas.add(blankArea);
        return this;
    }

    public SetBuilder addNeighborString(String neighborString){
        neighborStrings.add(neighborString);
        return this;
    }

    public SetBuilder addNeighbor(ISet neighbor){
        neighbors.add(neighbor);
        return this;
    }


    public Set getSet(){
        Set aSet = new Set(name, neighbors, area, blankAreas, neighborStrings);
        return aSet;
    }
}
