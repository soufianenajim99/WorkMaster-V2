package services;

import entities.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repositories.repositoryinterfaces.EmployeeRepository;
import services.serviceinterfaces.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }
}
