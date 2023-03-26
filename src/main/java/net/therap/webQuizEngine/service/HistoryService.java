package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.model.Answer;
import net.therap.webQuizEngine.model.History;
import net.therap.webQuizEngine.model.Summary;
import net.therap.webQuizEngine.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> findAll() {
        return historyRepository.findAllByUser();
    }

    public History saveOrUpdate(History history) {
        return historyRepository.save(history);
    }

    public void remove(History history) {
        historyRepository.delete(history);
    }

    public void process(Summary summary, Answer answer) {
        historyRepository.save(History.builder()
                .score(summary.getCurrentScore())
                .summaryLog(summary)
                .answer(answer)
                .build());
    }
}
