package ca.javau9.thyme.testmaker.repository;

import ca.javau9.thyme.testmaker.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}

