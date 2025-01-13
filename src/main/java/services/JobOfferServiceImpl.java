package services;

import dto.JobOfferListXML;
import dto.JobOfferXML;
import entities.JobOffer;
import jakarta.transaction.Transactional;
import repositories.repositoryinterfaces.JobOfferRepository;
import services.serviceinterfaces.JobOfferService;

import java.io.File;
import java.util.List;
import jakarta.inject.Inject;
import utils.XMLFileLoader;
import utils.XMLParser;
import utils.JobOfferXMLUtils.XMLWriter;
//import xmlTestingFiles.XMLWriter;


import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
public class JobOfferServiceImpl implements JobOfferService {
    private final JobOfferRepository jobOfferRepository;

    @Inject
    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }


    File XML_FILE = XMLFileLoader.getXMLFile("jobOffer/offres_emploi.xml");

    @Override
    @Transactional
    public JobOffer save(JobOffer jobOffer) {
        System.out.println("Using XML file at: " + XML_FILE.getAbsolutePath());

        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        try {
            // Parse the existing job offers from the XML file
//            JobOfferListXML jobOfferList = XMLParser.parseXML(XML_FILE);
            JobOfferListXML jobOfferList = XMLParser.parseXML(XML_FILE, JobOfferListXML.class);


            // Convert the new job offer to XML format
            JobOfferXML newOffer = toXML(savedJobOffer);

            // Check if the new offer already exists in the list
            boolean exists = jobOfferList.getOffers().stream()
                    .anyMatch(offer -> offer.getId().equals(newOffer.getId()));

            if (!exists) {
                // Add the new job offer only if it doesn't already exist
                jobOfferList.getOffers().add(newOffer);

                // Write the updated list back to the XML file
                XMLWriter.appendXML(jobOfferList, XML_FILE);

                System.out.println("Job offer added to XML.");
            } else {
                System.out.println("Job offer already exists in XML.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return savedJobOffer;

    }

    @Override
    public Optional<JobOffer> findById(UUID id) {
        return jobOfferRepository.findById(id);
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    @Override
    @Transactional
    public JobOffer update(JobOffer jobOffer) {
        // Update the job offer in the database
        JobOffer updatedJobOffer = jobOfferRepository.update(jobOffer);

        try {
            // Parse the existing job offers from the XML file
            JobOfferListXML jobOfferList = XMLParser.parseXML(XML_FILE, JobOfferListXML.class);


            // Find the job offer in the XML list and update its details
            boolean updated = false; // Flag to indicate if an update was performed
            for (JobOfferXML offerXML : jobOfferList.getOffers()) {
                if (offerXML.getId().toString().trim().equals(jobOffer.getJobId().toString().trim())) {
                    // Update the details of the matched job offer
                    offerXML.setTitle(jobOffer.getJobTitle());
                    offerXML.setDescription(jobOffer.getJobDescription());
                    offerXML.setStatus(jobOffer.getStatus().name());
                    offerXML.setPublicationDate(jobOffer.getPostedDate().toString());
                    offerXML.setExpirationDate(jobOffer.getValidUntil().toString());
                    updated = true;
                    break;
                }
            }

            if (updated) {
                // Write the updated list back to the XML file (overwrite)
                XMLWriter.writeXML(jobOfferList, XML_FILE);
                System.out.println("Job offer updated in XML.");
            } else {
                System.out.println("Job offer not found in XML for update.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedJobOffer;
    }


    @Override
    @Transactional
    public void deleteById(UUID id) {
        System.out.println( "delete by id method in service : " + id);
        try {
            // Parse the existing job offers from the XML file
            JobOfferListXML jobOfferList = XMLParser.parseXML(XML_FILE, JobOfferListXML.class);

            boolean removed = jobOfferList.getOffers().removeIf(offer -> {
                String offerId = offer.getId().toString().trim();
                String targetId = id.toString().trim();
                System.out.println("Comparing: " + offerId + " with " + targetId);
                return Objects.equals(offerId, targetId);
            });

            if (removed) {
                // Overwrite the XML file with the updated list
                XMLWriter.writeXML(jobOfferList, XML_FILE);
                System.out.println("Job offer deleted from XML.");
            } else {
                System.out.println("Job offer not found in XML for deletion.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//         Delete the job offer from the database
        jobOfferRepository.deleteById(id);
    }


    private JobOfferXML toXML(JobOffer jobOffer) {
        JobOfferXML xml = new JobOfferXML();
        xml.setId(jobOffer.getJobId());
        xml.setTitle(jobOffer.getJobTitle());
        xml.setDescription(jobOffer.getJobDescription());
        xml.setStatus(jobOffer.getStatus().name());
        xml.setPublicationDate(jobOffer.getPostedDate().toString());
        xml.setExpirationDate(jobOffer.getValidUntil().toString());
        return xml;
    }

}
