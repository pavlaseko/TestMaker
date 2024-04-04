package ca.javau9.thyme.testmaker.service.impl;

import ca.javau9.thyme.testmaker.model.Test;
import ca.javau9.thyme.testmaker.repository.TestRepository;
import ca.javau9.thyme.testmaker.service.TestService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found with id: " + id));
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public Test updateTest(Long id, Test test) {
        Test existingtest = getTestById(id);
        existingtest.setName(test.getName());
        testRepository.save(existingtest);
        return existingtest;
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<Test> getTestByAdminId(Long adminId) {
        return testRepository.findByAdminId(adminId);
    }
}
