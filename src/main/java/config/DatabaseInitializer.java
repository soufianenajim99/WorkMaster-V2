package config;

import entities.Admin;
import entities.Department;
import entities.Employee;
import entities.Recruiter;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
public class DatabaseInitializer {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        // Check if data already exists
        Long adminCount = em.createQuery("SELECT COUNT(a) FROM Admin a", Long.class).getSingleResult();
        Long employeeCount = em.createQuery("SELECT COUNT(e) FROM Employee e", Long.class).getSingleResult();
        Long recruiterCount = em.createQuery("SELECT COUNT(r) FROM Recruiter r", Long.class).getSingleResult();

        if (adminCount == 0 && employeeCount == 0 && recruiterCount == 0) {
            // Create sample departments
            Department hrDepartment = new Department("HR Department");
            Department itDepartment = new Department("IT Department");
            Department financeDepartment = new Department("Finance Department");

            em.persist(hrDepartment);
            em.persist(itDepartment);
            em.persist(financeDepartment);

            // Create 3 sample admins
            Admin admin1 = new Admin("123 Admin St, Adminville", "adminpass1", "admin1");
            Admin admin2 = new Admin("456 Admin Ave, Admintown", "adminpass2", "admin2");
            Admin admin3 = new Admin("789 Admin Blvd, Admincity", "adminpass3", "admin3");

            em.persist(admin1);
            em.persist(admin2);
            em.persist(admin3);

            // Create 3 sample employees
            Employee employee1 = new Employee(
                    "100 Employee Lane, Worktown", "emppass1", "employee1",
                    itDepartment, 75000.00,
                    LocalDate.of(1990, 5, 15), "123-45-6789",
                    LocalDate.of(2018, 3, 10), 4.5, 15.0, 2
            );

            Employee employee2 = new Employee(
                    "200 Worker St, Jobcity", "emppass2", "employee2",
                    hrDepartment, 65000.00,
                    LocalDate.of(1985, 8, 22), "987-65-4321",
                    LocalDate.of(2015, 7, 15), 4.8, 20.0, 3
            );

            Employee employee3 = new Employee(
                    "300 Labor Ave, Employeetown", "emppass3", "employee3",
                    financeDepartment, 85000.00,
                    LocalDate.of(1988, 11, 30), "456-78-9012",
                    LocalDate.of(2020, 1, 5), 4.2, 10.0, 1
            );

            em.persist(employee1);
            em.persist(employee2);
            em.persist(employee3);

            // Create 3 sample recruiters
            Recruiter recruiter1 = new Recruiter("400 Hiring St, Recruitville", "recpass1", "recruiter1");
            Recruiter recruiter2 = new Recruiter("500 Talent Ave, Hiretown", "recpass2", "recruiter2");
            Recruiter recruiter3 = new Recruiter("600 Candidate Blvd, Staffcity", "recpass3", "recruiter3");

            em.persist(recruiter1);
            em.persist(recruiter2);
            em.persist(recruiter3);
        }
    }
}
