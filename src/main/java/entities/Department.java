package entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy="department")
    private Set<Employee> employees;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
    }
}