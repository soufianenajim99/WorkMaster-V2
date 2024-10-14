package repositories;

import entities.Admin;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import repositories.repositoryinterfaces.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class AdminRepositoryImpl implements AdminRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    @Override
    public Admin save(Admin admin) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(admin);
            entityManager.getTransaction().commit();
            return admin;
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
    public Optional<Admin> findById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Admin.class, id));
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public List<Admin> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public Admin update(Admin admin) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Admin updatedAdmin = entityManager.merge(admin);
            entityManager.getTransaction().commit();
            return updatedAdmin;
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
            Admin admin = entityManager.find(Admin.class, id);
            if (admin != null) {
                entityManager.remove(admin);
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
