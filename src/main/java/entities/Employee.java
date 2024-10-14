package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="employees")
public class Employee extends User {
    @Column(name = "children")
    private int children_number;
    private double leaveBalance;
    private double performanceRating;
    private LocalDate hireDate;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    private double salary;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaveHistory;

    public List<Leave> getLeaveHistory() {
        return leaveHistory;
    }

    public void setLeaveHistory(List<Leave> leaveHistory) {
        this.leaveHistory = leaveHistory;
    }

    public Employee() {
    }

    public Employee(String address, String password, String username, Department department, double salary, LocalDate dateOfBirth, String socialSecurityNumber, LocalDate hireDate, double performanceRating, double leaveBalance, int children_number) {
        super(address, password, username);
        this.department = department;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.hireDate = hireDate;
        this.performanceRating = performanceRating;
        this.leaveBalance = leaveBalance;
        this.children_number = children_number;
    }
    public Employee(String address, String password, String username, double salary, LocalDate dateOfBirth, String socialSecurityNumber, LocalDate hireDate, double performanceRating, double leaveBalance, int children_number) {
        super(address, password, username);
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.hireDate = hireDate;
        this.performanceRating = performanceRating;
        this.leaveBalance = leaveBalance;
        this.children_number = children_number;
    }



    public Employee(UUID id, String username, String password, String address, int children_number, double leaveBalance, double performanceRating, LocalDate hireDate, String socialSecurityNumber, LocalDate dateOfBirth, double salary, Department department) {
        super(id, username, password, address);
        this.children_number = children_number;
        this.leaveBalance = leaveBalance;
        this.performanceRating = performanceRating;
        this.hireDate = hireDate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.department = department;
    }

    public int getChildren_number() {
        return children_number;
    }

    public void setChildren_number(int children_number) {
        this.children_number = children_number;
    }

    public double getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public double getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(double performanceRating) {
        this.performanceRating = performanceRating;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
