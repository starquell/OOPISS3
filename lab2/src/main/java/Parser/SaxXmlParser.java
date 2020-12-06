package Parser;

import Parser.PlaneBuilder;
import Parser.SaxHandler;
import Parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxXmlParser implements XMLParser {
    private PlaneBuilder planeBuilder;
    public SaxXmlParser(PlaneBuilder planeBuilder){
        this.planeBuilder = planeBuilder;
    }

    @Override
    public void parse(String pathToXmlDocument) throws IllegalArgumentException {

        var saxParserFactory = SAXParserFactory.newInstance();
        try {
            var saxParser = saxParserFactory.newSAXParser();
            var saxHandler = new SaxHandler(planeBuilder);
            saxParser.parse(new File(pathToXmlDocument), saxHandler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
    }
}