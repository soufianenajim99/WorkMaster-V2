package services;

import entities.JobOffer;
import jakarta.transaction.Transactional;
import repositories.repositoryinterfaces.JobOfferRepository;
import services.serviceinterfaces.JobOfferService;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import java.util.UUID;
public class JobOfferServiceImpl implements JobOfferService {
    private final JobOfferRepository jobOfferRepository;

    @Inject
    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @Override
    @Transactional
    public JobOffer save(JobOffer jobOffer) {
        // Additional validation logic if needed
        return jobOfferRepository.save(jobOffer);
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
        // Additional validation logic if needed
        return jobOfferRepository.update(jobOffer);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        jobOfferRepository.deleteById(id);
    }

}
