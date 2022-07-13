package cs345.deadwood.model;

import org.checkerframework.checker.units.qual.A;
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

        // iterate through sets and parse necessary information to store into Set object
        for (int i = 0; i < sets.getLength(); i++) {

            //get name of set
            Node set = sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();

            //lists to store set attributes
            List<IArea> blankAreas= new ArrayList<>();
            List<IArea> takeAreas= new ArrayList<>();
            List<String> neighborStrings = new ArrayList<>();
            List<IRole> rolesList = new ArrayList<>();

            //iterate through child nodes of each set
            NodeList setChildren = set.getChildNodes();
            for (int j = 0; j < setChildren.getLength(); j++) {
                Node child = setChildren.item(j);

                //get set area
                if ("area".equals(child.getNodeName())) {
                    area = getArea(child);
                }

                //get areas of blank spaces
                else if("blanks".equals(child.getNodeName())){
                    NodeList blankSpaces = child.getChildNodes();
                    for (int k = 0; k < blankSpaces.getLength(); k++) {
                        Node aBlank = blankSpaces.item(k);
                        if("blank".equals(aBlank.getNodeName())){
                            area1 = getArea(aBlank.getFirstChild());
                            blankAreas.add(area1);
                        }
                    }
                }

                //get areas of takes
                else if("takes".equals(child.getNodeName())){
                    NodeList blankSpaces1 = child.getChildNodes();
                    for (int l = blankSpaces1.getLength() -1; l >= 0 ; l--) {
                        Node aBlank1 = blankSpaces1.item(l);
                        if("take".equals(aBlank1.getNodeName())){
                            area1 = getArea(aBlank1.getFirstChild());
                            takeAreas.add(area1);
                        }
                    }
                }

                //get strings of neighbors which will be translated to their respective sets
                else if("neighbors".equals(child.getNodeName())){
                    NodeList neighborChildren = child.getChildNodes();
                    for(int n = 0; n<neighborChildren.getLength(); n++){
                        Node aNeighbor = neighborChildren.item(n);
                        if("neighbor".equals(aNeighbor.getNodeName())){
                            String neighborName = aNeighbor.getAttributes().getNamedItem("name").getNodeValue();
                            neighborStrings.add(neighborName);
                        }
                    }
                }

                //get roles for a set
                else if ("parts".equals(child.getNodeName())) {
                    NodeList partChildren = child.getChildNodes();
                    for(int m = 0; m<partChildren.getLength(); m++){
                        Node aPart = partChildren.item(m);
                        if("part".equals(aPart.getNodeName())){
                            String roleName = aPart.getAttributes().getNamedItem("name").getNodeValue();
                            int roleLevel = Integer.parseInt(aPart.getAttributes().getNamedItem("level").getNodeValue());
                            NodeList partChildren1 = aPart.getChildNodes();
                            String line = partChildren1.item(3).getTextContent();
                            Area area2 = getArea(partChildren1.item(1));
                            Role role = new Role(roleName, roleLevel, line, area2);
                            rolesList.add(role);
                        }
                    }
                }
            }

            Set aSet = new SetScene(setName, null, area, blankAreas, takeAreas, rolesList, null, neighborStrings);
            setsList.add(aSet);
        }

        //parse trailer separately due to different xml format
        NodeList trailer = rootNode.getElementsByTagName("trailer");
        String setName1 = "Trailer";
        List<IArea> blankAreas1= new ArrayList<>();
        List<String> neighborStrings1 = new ArrayList<>();
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
            }else if("neighbors".equals(child.getNodeName())){
                NodeList neighborChildren = child.getChildNodes();
                for(int n = 0; n<neighborChildren.getLength(); n++){
                    Node aNeighbor = neighborChildren.item(n);
                    if("neighbor".equals(aNeighbor.getNodeName())){
                        String neighborName = aNeighbor.getAttributes().getNamedItem("name").getNodeValue();
                        neighborStrings1.add(neighborName);
                    }
                }
            }
        }

        Set trailerSet = new Set(setName1, null, area, blankAreas1, neighborStrings1);
        setsList.add(trailerSet);

        //parse office separately due to different xml format
        NodeList office = rootNode.getElementsByTagName("office");
        String setName2 = "Office";
        List<IArea> blankAreas2= new ArrayList<>();
        List<String> neighborStrings2 = new ArrayList<>();
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
            }else if("neighbors".equals(child.getNodeName())){
                NodeList neighborChildren = child.getChildNodes();
                for(int n = 0; n<neighborChildren.getLength(); n++){
                    Node aNeighbor = neighborChildren.item(n);
                    if("neighbor".equals(aNeighbor.getNodeName())){
                        String neighborName = aNeighbor.getAttributes().getNamedItem("name").getNodeValue();
                        neighborStrings2.add(neighborName);
                    }
                }
            }
        }

        Set officeSet = new Set(setName2, null, area, blankAreas2, neighborStrings2);
        setsList.add(officeSet);

        //iterate through sets and add references to neighbors
        for(int p = 0; p < setsList.size(); p++){
            List<String> nStrings = setsList.get(p).getNeighborStrings();
            List<ISet> neighbors = convertNeighbors(nStrings,setsList);
            setsList.get(p).setNeighbors(neighbors);
        }
        return setsList;
    }

    //helper function to convert list of strings to their respective ISet object
    public List<ISet> convertNeighbors(List<String> nStrings, List<ISet> sets){
        List<ISet> neighbors = new ArrayList<>();
        for (int i = 0; i < nStrings.size(); i++){
            for (int j = 0; j < sets.size(); j++){
                if(nStrings.get(i).equals(sets.get(j).getName())){
                    neighbors.add(sets.get(j));
                }
            }
        }
        return neighbors;
    }

    //helper function to get area of node
    public Area getArea(Node areaNode){
        int x = Integer.parseInt(areaNode.getAttributes().getNamedItem("x").getNodeValue());
        int y = Integer.parseInt(areaNode.getAttributes().getNamedItem("y").getNodeValue());
        int h = Integer.parseInt(areaNode.getAttributes().getNamedItem("h").getNodeValue());
        int w = Integer.parseInt(areaNode.getAttributes().getNamedItem("w").getNodeValue());
        Area area = new Area(x,y,h,w);
        return area;
    }

}
