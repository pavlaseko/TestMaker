package ca.javau9.thyme.testmaker.repository;

import ca.javau9.thyme.testmaker.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin getAdminByEmail (String email);
    Boolean existsAdminByEmail (String email);
}
