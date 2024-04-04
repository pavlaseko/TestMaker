package ca.javau9.thyme.testmaker.service.impl;

import ca.javau9.thyme.testmaker.model.Admin;
import ca.javau9.thyme.testmaker.repository.AdminRepository;
import ca.javau9.thyme.testmaker.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void updateAdmin(Long id, Admin admin) {
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setNickname(admin.getNickname());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPassword(admin.getPassword());
        adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return adminRepository.existsAdminByEmail(email);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.getAdminByEmail(email);
    }
}
