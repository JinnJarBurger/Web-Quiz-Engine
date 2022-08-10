package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.model.Answer;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.Summary;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Service
public class SummaryService {

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private HistoryService historyService;

    public Summary findByUserAndQuiz(User user, Quiz quiz) {
        return summaryRepository.findByUserAndQuiz(user, quiz);
    }

    public Summary saveOrUpdate(Summary summary) {
        return summaryRepository.saveOrUpdate(summary);
    }

    public void remove(Summary summary) {
        summaryRepository.delete(summary);
    }

    public void process(User user, Quiz quiz, Answer answer) {
        Summary summary = getOrCreate(user, quiz);

        for (int i = 0; i < answer.getAnswers().size(); i++) {
            if (Objects.equals(quiz.getQuestions().get(i).getAnswer(), answer.getAnswers().get(i))) {
                summary.setCurrentScore(summary.getCurrentScore() + 1);
            } else {
                summary.getWrongQuestions().add(quiz.getQuestions().get(i));
                answer.getWrongAnswers().add(answer.getAnswers().get(i));
            }
        }

        if (summary.isNew()) {
            summary.setBestScore(summary.getCurrentScore());
        } else if (summary.getCurrentScore() > summary.getBestScore()){
            summary.setBestScore(summary.getCurrentScore());
        }

        answerService.saveOrUpdate(answer);
        summaryRepository.saveOrUpdate(summary);
        historyService.process(summary, answer);
    }

    private Summary getOrCreate(User user, Quiz quiz) {
        Summary summary = summaryRepository.findByUserAndQuiz(user, quiz);

        if (summary != null) {
            summary.setCurrentScore(0);
            summary.setWrongQuestions(new ArrayList<>());
        } else {
            summary = Summary.builder()
                    .wrongQuestions(new ArrayList<>())
                    .user(user)
                    .quiz(quiz)
                    .build();
        }

        return summary;
    }
}
