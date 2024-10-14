package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import services.serviceinterfaces.AuthenticationService;

public class AutenticationServiceImpl implements AuthenticationService {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public boolean authenticateAdmin(String username, String password) {
        return authenticateUser("Admin", username, password);
    }

    public boolean authenticateRecruiter(String username, String password) {
        return authenticateUser("Recruiter", username, password);
    }

    public boolean authenticateEmployee(String username, String password) {
        return authenticateUser("Employee", username, password);
    }

    private boolean authenticateUser(String entityName, String username, String password) {
        EntityManager entityManager = getEntityManager();
        try {
            String query = "SELECT u FROM " + entityName + " u WHERE u.username = :username AND u.password = :password";
            return entityManager.createQuery(query)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult() != null;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
}}
