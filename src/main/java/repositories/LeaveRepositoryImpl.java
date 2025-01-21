package repositories;

import entities.Leave;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.repositoryinterfaces.LeaveRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeaveRepositoryImpl implements LeaveRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public Leave save(Leave leave) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(leave);
            entityManager.getTransaction().commit();
            return leave;
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
    public Optional<Leave> findById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Leave.class, id));
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public List<Leave> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT j FROM Leave j", Leave.class).getResultList();
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public Leave update(Leave leave) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Leave updatedLeave = entityManager.merge(leave);
            entityManager.getTransaction().commit();
            return updatedLeave;
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
            Leave leave = entityManager.find(Leave.class, id);
            if (leave != null) {
                entityManager.remove(leave);
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

    @Override
    public List<Leave> findByEmployeeId(UUID employeeId) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT l FROM Leave l WHERE l.employee.id = :employeeId", Leave.class)
                    .setParameter("employeeId", employeeId)
                    .getResultList();
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

}
