package ca.javau9.thyme.testmaker.service.impl;

import ca.javau9.thyme.testmaker.model.Question;
import ca.javau9.thyme.testmaker.repository.QuestionRepository;
import ca.javau9.thyme.testmaker.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Question existingQuestion = getQuestionById(id);
        existingQuestion.setQuestionText(question.getQuestionText());
        questionRepository.save(existingQuestion);
        return existingQuestion;
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
