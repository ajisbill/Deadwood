package cs345.deadwood.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public abstract class GameDataParser {

    private final Document doc;

    public GameDataParser(String xmlFile) {
        doc = getDocFromFile(xmlFile);
    }

    private Document getDocFromFile(String filename) throws RuntimeException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            URL resource = getClass().getClassLoader().getResource(filename);
            return db.parse(resource.getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Can't read and parse the file " + filename);
        }
    }

    public Element getRootNode() {
        return doc.getDocumentElement();
    }
}
