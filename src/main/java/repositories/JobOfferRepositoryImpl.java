package repositories;

import entities.JobOffer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import repositories.repositoryinterfaces.JobOfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JobOfferRepositoryImpl implements JobOfferRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public JobOffer save(JobOffer jobOffer) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(jobOffer);
            entityManager.getTransaction().commit();
            return jobOffer;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle the exception as needed
        } finally {
            entityManager.close(); // Always close the EntityManager
        }
    }

    @Override
    public Optional<JobOffer> findById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(JobOffer.class, id));
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public List<JobOffer> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT j FROM JobOffer j", JobOffer.class).getResultList();
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public JobOffer update(JobOffer jobOffer) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            JobOffer updatedJobOffer = entityManager.merge(jobOffer);
            entityManager.getTransaction().commit();
            return updatedJobOffer;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle the exception as needed
        } finally {
            entityManager.close(); // Always close the EntityManager
        }
    }

    @Override
    public void deleteById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            JobOffer jobOffer = entityManager.find(JobOffer.class, id);
            if (jobOffer != null) {
                entityManager.remove(jobOffer);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle the exception as needed
        } finally {
            entityManager.close(); // Always close the EntityManager
        }
    }
}
