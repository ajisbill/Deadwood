package cs345.deadwood.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class CardParser extends GameDataParser {
    

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

        //iterate through cards to parse attributes and add to ICard object
        for (int i = 0; i < cards.getLength(); i++) {
            CardBuilder builder = new CardBuilder();

            Node card = cards.item(i);

            builder.addSceneName(card.getAttributes().getNamedItem("name").getNodeValue());
            builder.addImageName(card.getAttributes().getNamedItem("img").getNodeValue());
            builder.addBudget(Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue()));

            NodeList children = card.getChildNodes();
            int sceneNumber = 0;

            for (int j = 0; j < children.getLength(); j++) {
                Node sub = children.item(j);

                if ("scene".equals(sub.getNodeName())) {
                    builder.addSceneNumber(Integer.parseInt(sub.getAttributes().getNamedItem("number").getNodeValue()));
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
                    builder.addRole(role);
                }
            }
            Card aCard = builder.getCard();
            cardsList.add(aCard);
            System.out.println("\n");
        }
        return cardsList;
    }


}
