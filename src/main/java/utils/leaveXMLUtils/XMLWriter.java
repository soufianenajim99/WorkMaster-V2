package utils.leaveXMLUtils;

import dto.LeaveListXML;
import dto.LeaveXML;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class XMLWriter {
    public static void appendXML(LeaveListXML leaveList, File xmlFile) throws Exception {
        // Check if the XML file exists, if not create a new one
        if (!xmlFile.exists()) {
            // If the file does not exist, create a new file and add root structure
            JAXBContext context = JAXBContext.newInstance(LeaveListXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Create root element with an empty list if necessary
            leaveList.setLeaves(List.of());  // empty list if no offers are present initially
            marshaller.marshal(leaveList, xmlFile);
        }

        // Read the existing XML content (if any)
        LeaveListXML existingOffers = parseXML(xmlFile);

        // Loop through the new offers and add only the ones that do not already exist
        for (LeaveXML newLeave : leaveList.getLeaves()) {
            boolean exists = existingOffers.getLeaves().stream()
                    .anyMatch(offer -> offer.getRequestId().equals(newLeave.getRequestId()));

            if (!exists) {
                // Add the new offer if it does not already exist in the existing list
                existingOffers.getLeaves().add(newLeave);
            }
        }

        // Write the updated content back to the XML file
        JAXBContext context = JAXBContext.newInstance(LeaveListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(leaveList, xmlFile);

        System.out.println("Leave appended to XML at: " + xmlFile.getAbsolutePath());
    }

    // Parse existing XML to fetch current job offers
    public static LeaveListXML parseXML(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(LeaveListXML.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (LeaveListXML) unmarshaller.unmarshal(xmlFile);
    }
    public static void writeXML(LeaveListXML leaveList, File xmlFile) throws Exception {
        JAXBContext context = JAXBContext.newInstance(LeaveListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(leaveList, xmlFile);
        System.out.println("XML file overwritten at: " + xmlFile.getAbsolutePath());
    }
}
