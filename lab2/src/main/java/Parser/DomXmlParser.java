package Parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;

public class DomXmlParser implements XMLParser {

    private PlaneBuilder planeBuilder;
    private Logger log = Logger.getLogger(DomXmlParser.class.getName());

    DomXmlParser(PlaneBuilder planeBuilder){
        this.planeBuilder = planeBuilder;
    }

    @Override
    public void parse(String xmlPath) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.info(e.getMessage());
        }

        Element root = doc.getDocumentElement();

        NodeList planeNodes = root.getElementsByTagName(planeBuilder.getRootName());

        for (int i = 0; i < planeNodes.getLength(); i++) {

            Element planeElems = (Element) planeNodes.item(i);
            NodeList childNodes = planeElems.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

                    Element child = (org.w3c.dom.Element) childNodes.item(j);
                    var attrs = ((Node) child).getAttributes();
                    for (int k = 0; k < attrs.getLength(); k++) {
                        final var attr = (Attr) attrs.item(k);
                        planeBuilder.setTag(attr.getName(), attr.getValue());
                    }

                    planeBuilder.setTag(child.getNodeName(), getChildValue(planeElems, child.getNodeName()));
                    NodeList childChildNodes = child.getChildNodes();

                    for (int k = 0; k < childChildNodes.getLength(); k++) {
                        if (childChildNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                            Element childChild = (org.w3c.dom.Element) childChildNodes.item(k);
                            planeBuilder.setTag(childChild.getNodeName(), getChildValue(child, childChild.getNodeName()));
                        }
                    }
                }
            }
            planeBuilder.saveElement(planeElems.getNodeName());
        }
    }


    private String getChildValue(Element element, String name) {
        Element child = (Element) element.getElementsByTagName(name).item(0);
        if (child == null) {
            return "";
        }
        var node = child.getFirstChild();
        if (node == null) {
            return "";
        }
        return node.getNodeValue();
    }


}