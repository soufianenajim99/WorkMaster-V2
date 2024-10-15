package services;

import entities.Department;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repositories.repositoryinterfaces.DepartmentRepository;
import services.serviceinterfaces.DepartmentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Inject
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public Department save(Department department) {
        // Additional validation logic can be added here if needed
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> findById(UUID id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department update(Department department) {
        // Additional validation logic can be added here if needed
        return departmentRepository.update(department);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        departmentRepository.deleteById(id);
    }
}