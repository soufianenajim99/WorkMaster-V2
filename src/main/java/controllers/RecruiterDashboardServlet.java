package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;
import utils.XMLFileLoader;
import utils.XSLTransformer;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
@WebServlet("/recruiterDashboard")
public class RecruiterDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            File xmlFile = XMLFileLoader.getXMLFile("condidatures/condidature.xml");
            File xslFile = XMLFileLoader.getXMLFile("condidatures/condidature.xsl");
            File xsdFile = XMLFileLoader.getXMLFile("condidatures/condidature.xsd");
            validateXMLWithXSD(xmlFile, xsdFile);
            String htmlOutput = XSLTransformer.transformXML(xmlFile, xslFile);
            response.setContentType("text/html");
            response.getWriter().write(htmlOutput);

        }catch (SAXException e) {
        response.setContentType("text/plain");
        response.getWriter().write("XML validation error: " + e.getMessage());
        e.printStackTrace();
    }  catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void validateXMLWithXSD(File xmlFile, File xsdFile) throws SAXException, IOException {
        // Create a SchemaFactory for W3C XML Schema
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // Load the XSD schema file
        Schema schema = factory.newSchema(xsdFile);

        // Create a Validator to validate the XML
        Validator validator = schema.newValidator();

        // Validate the XML file against the XSD schema
        validator.validate(new StreamSource(xmlFile));
    }
}
