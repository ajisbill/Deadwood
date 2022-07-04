package cs345.deadwood.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class CardParser extends GameDataParser {

    public static void main(String[] args){

    }
    public CardParser() {
        super("cards.xml");
    }
        /**
         * Parse cards.xml to create a list of Card objects that implement the ICard interface.
         *
         * @return List of cards in cards.xml
         */


    public List<ICard> getCards() {

        List<ICard> cardsList = new ArrayList<>();
        Element rootNode = getRootNode();

        NodeList cards = rootNode.getElementsByTagName("card");

        for (int i = 0; i < cards.getLength(); i++) {
            System.out.println("Printing information for card " + (i + 1));
            Node card = cards.item(i);

            String sceneName = card.getAttributes().getNamedItem("name").getNodeValue();
            System.out.println("Scene: " + sceneName);

            String imageName = card.getAttributes().getNamedItem("img").getNodeValue();
            System.out.println("img: " + imageName);

            int budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
            System.out.println("budget: " + budget);

            NodeList children = card.getChildNodes();
            int sceneNumber = 0;
            List<IRole> rolesList = new ArrayList<>();
            for (int j = 0; j < children.getLength(); j++) {
                Node sub = children.item(j);

                if ("scene".equals(sub.getNodeName())) {
                    sceneNumber = Integer.parseInt(sub.getAttributes().getNamedItem("number").getNodeValue());
                    System.out.println("scene number: " + sceneNumber);
                }
                if ("part".equals(sub.getNodeName())) {
                    String roleName = sub.getAttributes().getNamedItem("name").getNodeValue();
                    int roleLevel = Integer.parseInt(sub.getAttributes().getNamedItem("level").getNodeValue());
                    System.out.println("roleName: " + roleName);
                    System.out.println("roleLevel: " + roleLevel);

                    NodeList partChildren = sub.getChildNodes();

                    int x = Integer.parseInt(partChildren.item(1).getAttributes().getNamedItem("x").getNodeValue());
                    int y = Integer.parseInt(partChildren.item(1).getAttributes().getNamedItem("y").getNodeValue());
                    int h = Integer.parseInt(partChildren.item(1).getAttributes().getNamedItem("h").getNodeValue());
                    int w = Integer.parseInt(partChildren.item(1).getAttributes().getNamedItem("w").getNodeValue());
                    String line = partChildren.item(3).getTextContent();
                    System.out.println("x: "+ x +", y: "+ y+ ", h: "+ h+ ", w: "+ w);
                    System.out.println("line: " + line);

                    Area area = new Area(x,y,h,w);
                    Role role = new Role(roleName, roleLevel, line, area);
                    rolesList.add(role);


                }
            }


            Card aCard = new Card(sceneName, imageName, budget, sceneNumber, rolesList);
            cardsList.add(aCard);
            System.out.println("\n");

        }

        return cardsList;
    }


}
