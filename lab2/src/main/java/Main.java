import Parser.PlanesParser;
import Planes.Planes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    private static String xmlPath = "src/main/resources/Planes.xml";
    private static String xsdPath = "src/main/resources/Planes.xsd";

    public static void main(String[]args) throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        PlanesParser planesParser = new PlanesParser();

        for (var parserType: PlanesParser.ParserType.values()) {
            planesParser.setTypeOfParser(parserType);
            Planes result = planesParser.parseXmlDocument(xmlPath, xsdPath);
            result.writeInFile("Planes.txt");
        }
    }
}
