package utils;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.io.StringWriter;

public class XSLTransformer {
    public static String transformXML(File xmlFile, File xslFile) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFile));

        StringWriter writer = new StringWriter();
        transformer.transform(new StreamSource(xmlFile), new StreamResult(writer));

        return writer.toString();
    }
}
