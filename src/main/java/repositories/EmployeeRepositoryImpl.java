package repositories;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.repositoryinterfaces.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public Employee save(Employee employee) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
            return employee;
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
    public Optional<Employee> findById(UUID id) {
        EntityManager entityManager = getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Employee.class, id));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Employee> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Employee update(Employee employee) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Employee updatedEmployee = entityManager.merge(employee);
            entityManager.getTransaction().commit();
            return updatedEmployee;
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
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                entityManager.remove(employee);
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
