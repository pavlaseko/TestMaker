package ca.javau9.thyme.testmaker.service;

import ca.javau9.thyme.testmaker.model.Answer;

import java.util.List;

public interface AnswerService {
    Answer createAnswer(Answer answer);
    Answer getAnswerById(Long id);
    List<Answer> getAllAnswers();
    Answer updateAnswer(Long id, Answer answer);
    void deleteAnswer(Long id);
}

