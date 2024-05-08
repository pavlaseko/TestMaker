package ca.javau9.thyme.testmaker.repository;

import ca.javau9.thyme.testmaker.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findTestByuserId (Long userID);
}