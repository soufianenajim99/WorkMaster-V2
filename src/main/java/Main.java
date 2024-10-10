//import entities.Department;
//import entities.Employee;
import entities.Department;
import entities.Employee;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Department dep1 = new Department("HR departement");

        Employee emp1 = new Employee(
                "123 Main St",                    // address
                "password123",                     // password
                "john_doe",                        // username
                dep1,                        // department
                50000.0,                           // salary
                LocalDate.of(1990, 1, 15),         // dateOfBirth
                "SSN123456789",                    // socialSecurityNumber
                LocalDate.of(2020, 5, 20),         // hireDate
                4.5,                               // performanceRating
                15.0,                              // leaveBalance
                2                                  // children_number
        );

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(dep1);
        entityManager.persist(emp1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
