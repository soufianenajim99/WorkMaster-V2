package repositories;

import entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.repositoryinterfaces.DepartmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public Department save(Department department) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(department);
            entityManager.getTransaction().commit();
            return department;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Department> findById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Department.class, id));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Department> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Department update(Department department) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Department updatedDepartment = entityManager.merge(department);
            entityManager.getTransaction().commit();
            return updatedDepartment;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Department department = entityManager.find(Department.class, id);
            if (department != null) {
                entityManager.remove(department);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
