package net.therap.webQuizEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answer")
public class Answer extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;

    @ElementCollection
    @OrderColumn(name = "order_answer")
    private List<String> answers;

    @ElementCollection
    @OrderColumn(name = "order_wrong_answer")
    private List<String> wrongAnswers;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_quiz")
    private Quiz quiz;

    public Answer() {
        answers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();
    }

    public boolean isNew() {
        return getId() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Answer)) {
            return false;
        }
        Answer that = (Answer) obj;

        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
