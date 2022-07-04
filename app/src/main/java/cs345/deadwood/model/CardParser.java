package cs345.deadwood.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

        Element rootNode = getRootNode();

        NodeList cards = rootNode.getElementsByTagName("card");

        for (int i = 0; i < cards.getLength(); i++){
            System.out.println("Printing information for card " + (i + 1));
            Node card = cards.item(i);

            String sceneName = card.getAttributes().getNamedItem("name").getNodeValue();
            String imageName = card.getAttributes().getNamedItem("img").getNodeValue();
            int budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());

            System.out.println("Scene: " + sceneName);
            System.out.println("img: " + imageName);
            System.out.println("budget: " + budget);
            System.out.println("\n");

        }

        return null;
    }


}
