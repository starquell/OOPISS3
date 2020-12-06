package Parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

    private PlaneBuilder planeBuilder;
    private StringBuilder data;

    public SaxHandler(PlaneBuilder planeBuilder){
        this.planeBuilder = planeBuilder;
        data = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase(planeBuilder.getRootName())) {
            planeBuilder.saveElement(qName);
        }
        planeBuilder.setTag(qName, data.toString());
        data = new StringBuilder();
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }
}