package repositories;

import entities.Condidature;
import repositories.repositoryinterfaces.CondidatureRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
public class CondidatureRepositoryImpl implements CondidatureRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public Condidature save(Condidature condidature) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(condidature);
            entityManager.getTransaction().commit();
            return condidature;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle exception as needed
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public Optional<Condidature> findById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Condidature.class, id));
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public List<Condidature> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Condidature c", Condidature.class).getResultList();
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public Condidature update(Condidature condidature) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Condidature updatedCondidature = entityManager.merge(condidature);
            entityManager.getTransaction().commit();
            return updatedCondidature;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle exception as needed
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public void deleteById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Condidature condidature = entityManager.find(Condidature.class, id);
            if (condidature != null) {
                entityManager.remove(condidature);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle exception as needed
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }
}
