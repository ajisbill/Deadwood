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
        Area area = null;
        Area area1 = null;


        for (int i = 0; i < sets.getLength(); i++) {
            List<IRole> rolesList = new ArrayList<>();
            Node set = sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();

            NodeList setChildren = set.getChildNodes();

            List<IArea> blankAreas= new ArrayList<>();
            List<IArea> takeAreas= new ArrayList<>();

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
                }else if("takes".equals(child.getNodeName())){
                    NodeList blankSpaces1 = child.getChildNodes();
                    for (int l = blankSpaces1.getLength() -1; l >= 0 ; l--) {
                        Node aBlank1 = blankSpaces1.item(l);
                        if("take".equals(aBlank1.getNodeName())){
                            area1 = getArea(aBlank1.getFirstChild());
                            takeAreas.add(area1);
                        }
                    }
                }else if ("parts".equals(child.getNodeName())) {
                    NodeList partChildren = child.getChildNodes();
                    for(int m = 0; m<partChildren.getLength(); m++){
                        Node aPart = partChildren.item(m);
                        if("part".equals(aPart.getNodeName())){
                            String roleName = aPart.getAttributes().getNamedItem("name").getNodeValue();
                            int roleLevel = Integer.parseInt(aPart.getAttributes().getNamedItem("level").getNodeValue());
                            System.out.println("roleName: " + roleName);
                            System.out.println("roleLevel: " + roleLevel);

                            NodeList partChildren1 = aPart.getChildNodes();

                            int x = Integer.parseInt(partChildren1.item(1).getAttributes().getNamedItem("x").getNodeValue());
                            int y = Integer.parseInt(partChildren1.item(1).getAttributes().getNamedItem("y").getNodeValue());
                            int h = Integer.parseInt(partChildren1.item(1).getAttributes().getNamedItem("h").getNodeValue());
                            int w = Integer.parseInt(partChildren1.item(1).getAttributes().getNamedItem("w").getNodeValue());
                            String line = partChildren1.item(3).getTextContent();
                            System.out.println("x: "+ x +", y: "+ y+ ", h: "+ h+ ", w: "+ w);
                            System.out.println("line: " + line);

                            Area area2 = new Area(x,y,h,w);
                            Role role = new Role(roleName, roleLevel, line, area2);
                            rolesList.add(role);
                        }
                    }



                }
            }

            Set aSet = new Set(setName, null, area, blankAreas, null, rolesList, takeAreas);
            setsList.add(aSet);
        }

        NodeList trailer = rootNode.getElementsByTagName("trailer");
        String setName1 = "Trailer";
        List<IArea> blankAreas1= new ArrayList<>();
        System.out.println(trailer.getLength());
        Node trailerNode = trailer.item(0);
        NodeList trailerChildren = trailerNode.getChildNodes();
        for (int i = 0; i < trailerChildren.getLength(); i++){
            Node child = trailerChildren.item(i);
            if ("area".equals(child.getNodeName())) {
                area = getArea(child);
            } else if ("blanks".equals(child.getNodeName())) {
                NodeList blankSpaces = child.getChildNodes();
                for (int k = 0; k < blankSpaces.getLength(); k++) {
                    Node aBlank = blankSpaces.item(k);
                    if ("blank".equals(aBlank.getNodeName())) {
                        area1 = getArea(aBlank.getFirstChild());
                        blankAreas1.add(area1);
                    }
                }
            }
        }

        Set aSet = new Set(setName1, null, area, blankAreas1, null, null, null);
        setsList.add(aSet);

        NodeList office = rootNode.getElementsByTagName("office");
        String setName2 = "Office";
        List<IArea> blankAreas2= new ArrayList<>();
        Node officeNode = office.item(0);
        NodeList officeChildren = officeNode.getChildNodes();
        for (int i = 0; i < officeChildren.getLength(); i++){
            Node child = officeChildren.item(i);
            if ("area".equals(child.getNodeName())) {
                area = getArea(child);
            } else if ("blanks".equals(child.getNodeName())) {
                NodeList blankSpaces = child.getChildNodes();
                for (int k = 0; k < blankSpaces.getLength(); k++) {
                    Node aBlank = blankSpaces.item(k);
                    if ("blank".equals(aBlank.getNodeName())) {
                        area1 = getArea(aBlank.getFirstChild());
                        blankAreas2.add(area1);
                    }
                }
            }
        }

        Set aSet2 = new Set(setName2, null, area, blankAreas2, null, null, null);
        setsList.add(aSet2);


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
