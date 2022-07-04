package cs345.deadwood.model;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class SetParser extends GameDataParser {

    public SetParser() {
        super("board.xml");
    }

    /**
     * Parse board.xml to create a list of set objects that implement the ISet or ISetScene interface.
     *
     * @return List of Sets in board.xml
     */
    public List<ISet> getSets() {

        Element rootNode = getRootNode();

        List<ISet> setsList = new ArrayList<>();

        NodeList sets = rootNode.getElementsByTagName("set");

        for (int i = 0; i < sets.getLength(); i++) {
            Node set = sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();

            NodeList setChildren = set.getChildNodes();
            Area area = null;
            Area area1 = null;
            List<IArea> blankAreas= new ArrayList<>();

            for (int j = 0; j < setChildren.getLength(); j++) {
                Node child = setChildren.item(j);
                if ("area".equals(child.getNodeName())) {
                    area = getArea(child);
                } else if("blanks".equals(child.getNodeName())){
                    NodeList blankSpaces = child.getChildNodes();
                    for (int k = 0; k < blankSpaces.getLength(); k++) {
                        Node aBlank = blankSpaces.item(k);
                        if("blank".equals(aBlank.getNodeName())){
                            area1 = getArea(aBlank.getFirstChild());
                            blankAreas.add(area1);
                        }
                    }
                }
            }

            Set aSet = new Set(setName, null, area, blankAreas);
            setsList.add(aSet);
        }



        return setsList;
    }

    public Area getArea(Node areaNode){
        int x = Integer.parseInt(areaNode.getAttributes().getNamedItem("x").getNodeValue());
        int y = Integer.parseInt(areaNode.getAttributes().getNamedItem("y").getNodeValue());
        int h = Integer.parseInt(areaNode.getAttributes().getNamedItem("h").getNodeValue());
        int w = Integer.parseInt(areaNode.getAttributes().getNamedItem("w").getNodeValue());
        Area area = new Area(x,y,h,w);
        return area;
    }

}
