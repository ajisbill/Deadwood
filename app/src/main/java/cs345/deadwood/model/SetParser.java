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
                    //System.out.println(child.getNodeValue());
                    int x = Integer.parseInt(child.getAttributes().getNamedItem("x").getNodeValue());
                    int y = Integer.parseInt(child.getAttributes().getNamedItem("y").getNodeValue());
                    int h = Integer.parseInt(child.getAttributes().getNamedItem("h").getNodeValue());
                    int w = Integer.parseInt(child.getAttributes().getNamedItem("w").getNodeValue());
                    area = new Area(x, y, h, w);
                }
            }

            Set aSet = new Set(setName, null, area, blankAreas);
            setsList.add(aSet);
        }



        return setsList;
    }

}
