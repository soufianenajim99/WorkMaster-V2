package utils.employeeXMLUtils;

import dto.EmployeeListXML;
import dto.EmployeeXML;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class XMLWriter {
    public static void appendXML(EmployeeListXML jobOfferList, File xmlFile) throws Exception {
        // Check if the XML file exists, if not create a new one
        if (!xmlFile.exists()) {
            // If the file does not exist, create a new file and add root structure
            JAXBContext context = JAXBContext.newInstance(EmployeeListXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Create root element with an empty list if necessary
            jobOfferList.setEmployees(List.of());  // empty list if no offers are present initially
            marshaller.marshal(jobOfferList, xmlFile);
        }

        // Read the existing XML content (if any)
        EmployeeListXML existingOffers = parseXML(xmlFile);

        // Loop through the new offers and add only the ones that do not already exist
        for (EmployeeXML newOffer : jobOfferList.getEmployees()) {
            boolean exists = existingOffers.getEmployees().stream()
                    .anyMatch(offer -> offer.getId().equals(newOffer.getId()));

            if (!exists) {
                // Add the new offer if it does not already exist in the existing list
                existingOffers.getEmployees().add(newOffer);
            }
        }

        // Write the updated content back to the XML file
        JAXBContext context = JAXBContext.newInstance(EmployeeListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(jobOfferList, xmlFile);

        System.out.println("Employee appended to XML at: " + xmlFile.getAbsolutePath());
    }

    // Parse existing XML to fetch current job offers
    public static EmployeeListXML parseXML(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(EmployeeListXML.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (EmployeeListXML) unmarshaller.unmarshal(xmlFile);
    }
    public static void writeXML(EmployeeListXML jobOfferList, File xmlFile) throws Exception {
        JAXBContext context = JAXBContext.newInstance(EmployeeListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(jobOfferList, xmlFile);
        System.out.println("XML file overwritten at: " + xmlFile.getAbsolutePath());
    }
}
