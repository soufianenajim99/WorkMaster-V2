package services;

import entities.Condidature;
import repositories.repositoryinterfaces.CondidatureRepository;
import services.serviceinterfaces.CondidatureService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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

    @Override
    @Transactional
    public Condidature save(Condidature condidature) {
        // Additional validation logic can be added here
        return condidatureRepository.save(condidature);
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
        // Additional validation logic can be added here
        return condidatureRepository.update(condidature);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        condidatureRepository.deleteById(id);
    }
}