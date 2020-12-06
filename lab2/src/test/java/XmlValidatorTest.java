import Parser.XmlValidator;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    @Test
    public void validXMLvalidation(){
        Assert.assertTrue(
                XmlValidator.validate("src/main/resources/Planes.xml", "src/main/resources/Planes.xsd"))
        ;
    }
}
