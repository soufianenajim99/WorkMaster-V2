package dto;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import utils.UUIDAdapter;

import java.time.LocalDate;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXML {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(UUIDAdapter.class)
    private UUID id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Address")
    private String address;

    @XmlElement(name = "ChildrenNumber")
    private int childrenNumber;

    @XmlElement(name = "LeaveBalance")
    private double leaveBalance;

    @XmlElement(name = "PerformanceRating")
    private double performanceRating;

    @XmlElement(name = "HireDate")
    private LocalDate hireDate;

    @XmlElement(name = "SocialSecurityNumber")
    private String socialSecurityNumber;

    @XmlElement(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @XmlElement(name = "Salary")
    private double salary;

    @XmlElement(name = "Department")
    private String department; // Using department name or ID

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
