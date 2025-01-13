package utils.condidatureXMLUtils;

import dto.CondidatureListXML;
import dto.CondidatureXML;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class XMLWriter {
    public static void appendXML(CondidatureListXML condidatureList, File xmlFile) throws Exception {
        // Check if the XML file exists, if not create a new one
        if (!xmlFile.exists()) {
            // If the file does not exist, create a new file and add root structure
            JAXBContext context = JAXBContext.newInstance(CondidatureListXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Create root element with an empty list if necessary
            condidatureList.setCondidatures(List.of());  // empty list if no offers are present initially
            marshaller.marshal(condidatureList, xmlFile);
        }

        // Read the existing XML content (if any)
        CondidatureListXML existingOffers = parseXML(xmlFile);

        // Loop through the new offers and add only the ones that do not already exist
        for (CondidatureXML newOffer : condidatureList.getCondidatures()) {
            boolean exists = existingOffers.getCondidatures().stream()
                    .anyMatch(offer -> offer.getId().equals(newOffer.getId()));

            if (!exists) {
                // Add the new offer if it does not already exist in the existing list
                existingOffers.getCondidatures().add(newOffer);
            }
        }

        // Write the updated content back to the XML file
        JAXBContext context = JAXBContext.newInstance(CondidatureListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(condidatureList, xmlFile);

        System.out.println("Job offers appended to XML at: " + xmlFile.getAbsolutePath());
    }

    // Parse existing XML to fetch current job offers
    public static CondidatureListXML parseXML(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CondidatureListXML.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (CondidatureListXML) unmarshaller.unmarshal(xmlFile);
    }
    public static void writeXML(CondidatureListXML condidatureList, File xmlFile) throws Exception {
        JAXBContext context = JAXBContext.newInstance(CondidatureListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(condidatureList, xmlFile);
        System.out.println("XML file overwritten at: " + xmlFile.getAbsolutePath());
    }
}
