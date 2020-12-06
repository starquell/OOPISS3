import Parser.PlaneBuilder;
import Parser.PlanesParser;

import Planes.Chars;
import Planes.Params;
import Planes.Plane;
import Planes.Planes;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    PlanesParser parser;
    Plane[] plane;

    public ParserTest(){
        parser = new PlanesParser();
        plane = new Plane[] {new Plane(), new Plane()};

        plane[0].setModel("AH-64D Longbow");
        plane[0].setOrigin("USA");
        plane[0].setParams(new Params(6.4, 3, 2));
        plane[0].setChars(new Chars("Attack", 2, true));
        plane[0].setPrice(12200000);

        plane[1].setModel("Mil Mi-28N");
        plane[1].setOrigin("Russia");
        plane[1].setParams(new Params(8, 3.8, 2.3));
        plane[1].setChars(new Chars("Attack",1,true));
        plane[1].setPrice(21550000);
    }

    static void parseAndCompare (Plane[] planes, PlanesParser parser) throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        Planes parsedPlanes = parser.parseXmlDocument(
                "src/main/resources/Planes.xml",
                "src/main/resources/Planes.xsd"
        );
        assertEquals(0, parsedPlanes.getPlanes().get(0).compareTo(planes[0]));
        assertEquals(0, parsedPlanes.getPlanes().get(1).compareTo(planes[1]));
    }

    @Test
    public void DOMParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        parser.setTypeOfParser(PlanesParser.ParserType.DOM);
        parseAndCompare(plane, parser);
    }

    @Test
    public void SAXParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        parser.setTypeOfParser(PlanesParser.ParserType.SAX);
        parseAndCompare(plane, parser);
    }

    @Test
    public void StaxParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        parser.setTypeOfParser(PlanesParser.ParserType.STAX);
        parseAndCompare(plane, parser);
    }
}