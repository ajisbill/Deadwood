package cs345.deadwood.model;

import org.w3c.dom.Element;

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

        // TODO: Implement

        return null;
    }

}
