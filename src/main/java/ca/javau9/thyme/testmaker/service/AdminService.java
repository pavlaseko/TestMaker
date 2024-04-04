package ca.javau9.thyme.testmaker.service;

import ca.javau9.thyme.testmaker.model.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    void updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
    Admin getAdminByEmail(String email);
    boolean existsByEmail(String email);
}
