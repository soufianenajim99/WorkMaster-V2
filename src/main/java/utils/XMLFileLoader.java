package utils;

import jakarta.servlet.ServletContext;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class XMLFileLoader {
    private static ServletContext servletContext;

    // Method to initialize the ServletContext
    public static void setServletContext(ServletContext context) {
        servletContext = context;
    }

    public static File getXMLFile(String fileName) {
        if (servletContext == null) {
            throw new IllegalStateException("ServletContext not initialized. Call setServletContext first.");
        }

        // Get the real path of the data/jobOffer directory
        String basePath = servletContext.getRealPath("/WEB-INF/data");

        // Define the directory for storing the XML file
        File directory = new File(basePath);

        // Ensure the directory exists
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            } else {
                throw new RuntimeException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        // Define the XML file path
        File xmlFile = new File(directory, fileName);

        // Ensure the XML file exists
        if (!xmlFile.exists()) {
            try {
                if (xmlFile.createNewFile()) {
                    System.out.println("File created: " + xmlFile.getAbsolutePath());
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create the file: " + xmlFile.getAbsolutePath(), e);
            }
        }

        return xmlFile;
    }

    public static File createXMLFileFromObject(Object object, String filePath, ServletContext context) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();

        // Pretty print the XML output (optional)
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Get the real path for the file
        String realPath = context.getRealPath("/WEB-INF/data/") + filePath;

        // Create the file
        File xmlFile = new File(realPath);

        // Ensure the directory exists, create if necessary
        File parentDir = xmlFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs(); // Create parent directories if they don't exist
        }

        // Marshal the object into the XML file
        marshaller.marshal(object, xmlFile);

        return xmlFile; // Return the file reference
    }

}
