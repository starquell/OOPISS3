package Parser;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    public static boolean validate(String xmlPath, String xsdPath) {
        try {
            var factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(new File(xsdPath));
            var validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException | IOException e) {
            System.out.printf("Validation error : %s%n", e.getMessage());
            return false;
        }
        return true;
    }
}
