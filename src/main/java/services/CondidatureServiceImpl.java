package services;

import dto.CondidatureListXML;
import dto.CondidatureXML;
import dto.JobOfferListXML;
import dto.JobOfferXML;
import entities.Condidature;
import entities.JobOffer;
import enums.Status;
import repositories.repositoryinterfaces.CondidatureRepository;
import services.serviceinterfaces.CondidatureService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import utils.XMLFileLoader;
import utils.XMLParser;
import utils.condidatureXMLUtils.XMLWriter;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@ApplicationScoped
public class CondidatureServiceImpl implements CondidatureService {

    private final CondidatureRepository condidatureRepository;

    @Inject
    public CondidatureServiceImpl(CondidatureRepository condidatureRepository) {
        this.condidatureRepository = condidatureRepository;
    }
    File XML_FILE = XMLFileLoader.getXMLFile("condidatures/condidature.xml");
    @Override
    @Transactional
    public Condidature save(Condidature condidature) {
        // Save to database
        Condidature savedCondidature = condidatureRepository.save(condidature);

        try {
            // Parse the existing condidatures from the XML file
            CondidatureListXML condidatureList = XMLParser.parseXML(XML_FILE, CondidatureListXML.class);

            // Convert the new condidature to XML format
            CondidatureXML newCondidature = toXML(savedCondidature);

            // Check if it already exists
            boolean exists = condidatureList.getCondidatures().stream()
                    .anyMatch(c -> c.getId().equals(newCondidature.getId()));

            if (!exists) {
                condidatureList.getCondidatures().add(newCondidature);
                XMLWriter.writeXML(condidatureList, XML_FILE);
                System.out.println("Condidature added to XML.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedCondidature;
    }


    @Override
    public Optional<Condidature> findById(UUID id) {
        return condidatureRepository.findById(id);
    }

    @Override
    public List<Condidature> findAll() {
        return condidatureRepository.findAll();
    }

    @Override
    @Transactional
    public Condidature update(Condidature condidature) {

        Condidature updatedCondidature = condidatureRepository.update(condidature);

        try {
            // Parse the existing job offers from the XML file
            CondidatureListXML condidatureList = XMLParser.parseXML(XML_FILE, CondidatureListXML.class);


            // Find the job offer in the XML list and update its details
            boolean updated = false; // Flag to indicate if an update was performed
            for (CondidatureXML condxml : condidatureList.getCondidatures()) {
                if (condxml.getId().toString().trim().equals(condidature.getId().toString().trim())) {
                    // Update the details of the matched job offer
                    condxml.setStatus(condidature.getStatus().name());
                    updated = true;
                    break;
                }
            }

            if (updated) {
                // Write the updated list back to the XML file (overwrite)
                utils.condidatureXMLUtils.XMLWriter.writeXML(condidatureList, XML_FILE);
                System.out.println("Job offer updated in XML.");
            } else {
                System.out.println("Job offer not found in XML for update.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedCondidature;
    }

    @Transactional
    public void acceptCondidature(UUID id) {
        Condidature condidature = condidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condidature not found"));

        condidature.setStatus(Status.ACCEPTED);
        update(condidature);
    }

    @Transactional
    public void rejectCondidature(UUID id) {
        Condidature condidature = condidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condidature not found"));

        condidature.setStatus(Status.REJECTED);
        update(condidature);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        condidatureRepository.deleteById(id);
    }

    private CondidatureXML toXML(Condidature condidature) {
        CondidatureXML xml = new CondidatureXML();
        xml.setId(condidature.getId());
        xml.setName(condidature.getName());
        xml.setEmail(condidature.getEmail());
        xml.setStatus(condidature.getStatus().name());

        // Set the job offer ID from the associated JobOffer entity
        if (condidature.getJobOffer() != null) {
            xml.setJobOfferId(condidature.getJobOffer().getJobId().toString());
        }

        return xml;
    }

}