package ca.javau9.thyme.testmaker.service;

import ca.javau9.thyme.testmaker.model.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
}

