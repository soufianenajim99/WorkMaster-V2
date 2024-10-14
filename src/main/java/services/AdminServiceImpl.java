package services;

import entities.Admin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repositories.repositoryinterfaces.AdminRepository;
import services.serviceinterfaces.AdminService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Inject
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public Admin save(Admin admin) {
        // Additional validation logic
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> findById(UUID id) {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional
    public Admin update(Admin admin) {
        // Additional validation logic
        return adminRepository.update(admin);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        adminRepository.deleteById(id);
    }
}
