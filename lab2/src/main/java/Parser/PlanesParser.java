package Parser;

import Planes.Planes;
import org.xml.sax.SAXException;

import javax.swing.text.html.parser.Parser;
import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class PlanesParser {

    private XMLParser parser;
    private Planes result;
    private PlaneBuilder planeBuilder = new PlaneBuilder();

    public enum ParserType {
        SAX,
        DOM,
        STAX
    }
    public void setTypeOfParser(ParserType t){
        parser = switch (t) {
            case SAX -> new SaxXmlParser(planeBuilder);
            case DOM -> new DomXmlParser(planeBuilder);
            case STAX -> new StaxXmlParser(planeBuilder);
        };
    }
    public Planes parseXmlDocument(String pathToXmlDocument, String pathToXsdFile) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        if (XmlValidator.validate(pathToXmlDocument, pathToXsdFile)) {
            parser.parse(pathToXmlDocument);
            result = new Planes(planeBuilder.getPlanes());
            result.sort();
        }
        return result;
    }
}
