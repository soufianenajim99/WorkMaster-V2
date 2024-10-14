//import entities.Department;
//import entities.Employee;
import entities.*;
import enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import repositories.AdminRepositoryImpl;
import repositories.repositoryinterfaces.AdminRepository;
import services.AdminServiceImpl;
import services.serviceinterfaces.AdminService;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Department dep1 = new Department("HR departement");
//
//        Employee emp1 = new Employee(
//                "123 Main St",                    // address
//                "password123",                     // password
//                "john_doe",                        // username
//                dep1,                        // department
//                50000.0,                           // salary
//                LocalDate.of(1990, 1, 15),         // dateOfBirth
//                "SSN123456789",                    // socialSecurityNumber
//                LocalDate.of(2020, 5, 20),         // hireDate
//                4.5,                               // performanceRating
//                15.0,                              // leaveBalance
//                2                                  // children_number
//        );
//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(dep1);
//        entityManager.persist(emp1);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();

//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();

        // Initialize AdminRepository and AdminService
        AdminRepository adminRepository = new AdminRepositoryImpl();
        AdminService adminService = new AdminServiceImpl(adminRepository);


//            transaction.begin();

            // Test 1: Create and Persist an Admin
           Optional<Admin> admin = adminService.findById(UUID.fromString("97570ba4-d9d4-4fdc-9675-fbd24e260884"));
        System.out.println(admin);
            // Test 2: Retrieve and Display the Admin
//            Optional<Admin> savedAdmin = adminService.findById(admin.getId());
//            if (savedAdmin.isPresent()) {
//                Admin adminFound = savedAdmin.get();
//                System.out.println("Admin Retrieved: " + adminFound.getUsername() + " " + adminFound.getAddress());
//            } else {
//                System.out.println("Admin not found.");
//            }

//            // Test 3: Update the Admin
//            savedAdmin.ifPresent(adminToUpdate -> {
//                adminToUpdate.setUsername("Johnson"); // Change last name
//                adminService.update(adminToUpdate);   // Save the update
//                System.out.println("Admin updated successfully!");
//            });

            // Test 4: Delete the Admin
//            savedAdmin.ifPresent(adminToDelete -> {
//                adminService.deleteById(adminToDelete.getId());
//                System.out.println("Admin deleted successfully!");
//            });

//            transaction.commit();


    }
}
