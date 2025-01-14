package utils;

import jakarta.servlet.ServletContext;

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
}
