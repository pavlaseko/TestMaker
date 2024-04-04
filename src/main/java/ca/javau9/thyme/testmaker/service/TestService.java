package ca.javau9.thyme.testmaker.service;

import ca.javau9.thyme.testmaker.model.Test;

import java.util.List;

public interface TestService {
    Test createTest(Test test);
    Test getTestById(Long id);
    List<Test> getAllTests();
    Test updateTest(Long id, Test test);
    void deleteTest(Long id);

    List<Test> getTestByAdminId(Long adminId);
}
