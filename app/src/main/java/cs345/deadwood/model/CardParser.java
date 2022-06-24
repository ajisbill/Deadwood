package cs345.deadwood.model;

import org.w3c.dom.Element;

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

        Element rootNode = getRootNode();

        // TODO: Implement

        return null;
    }


}
