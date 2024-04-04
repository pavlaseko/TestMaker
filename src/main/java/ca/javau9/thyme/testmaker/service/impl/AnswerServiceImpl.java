package ca.javau9.thyme.testmaker.service.impl;

import ca.javau9.thyme.testmaker.model.Answer;
import ca.javau9.thyme.testmaker.repository.AnswerRepository;
import ca.javau9.thyme.testmaker.service.AnswerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found with id: " + id));
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer updateAnswer(Long id, Answer answer) {
        Answer existingAnswer = getAnswerById(id);
        existingAnswer.setAnswerText(answer.getAnswerText());
        answerRepository.save(existingAnswer);
        return existingAnswer;
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
