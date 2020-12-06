package Parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class StaxXmlParser implements XMLParser {
    private final PlaneBuilder planeBuilder;

    StaxXmlParser(PlaneBuilder planeBuilder) {
        this.planeBuilder = planeBuilder;
    }

    @Override
    public void parse(String pathToXmlDocument) {
        XMLInputFactory xmlReaderFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlReader = null;
        try {
            xmlReader = xmlReaderFactory.createXMLEventReader(new FileInputStream(pathToXmlDocument));
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println(e.getMessage());
            return;
        }

        while (xmlReader.hasNext()) {
            try {
                XMLEvent event = xmlReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_DOCUMENT -> {
                        event = xmlReader.nextEvent();
                    }
                    case XMLStreamConstants.END_DOCUMENT -> {}

                    case XMLStreamConstants.START_ELEMENT -> {
                        StartElement startElement = event.asStartElement();
                        final var attrIt = startElement.getAttributes();

                        if (attrIt.hasNext()) {
                            while (attrIt.hasNext()) {
                                final var attr = attrIt.next();
                                planeBuilder.setTag(attr.getName().getLocalPart(), attr.getValue());
                            }
                        } else {
                            event = xmlReader.nextEvent();
                            planeBuilder.setTag(startElement.getName().getLocalPart(), event.asCharacters().getData());
                        }
                    }
                    case XMLStreamConstants.END_ELEMENT -> {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase(planeBuilder.getRootName())) {
                            planeBuilder.saveElement(planeBuilder.getRootName());
                        }
                    }
                }
            } catch (XMLStreamException e) {
               System.out.println(e.getMessage());
            }
        }
    }

}