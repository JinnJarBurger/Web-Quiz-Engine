package net.therap.webQuizEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author adnan
 * @since 7/20/2022
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "summary",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fk_user", "fk_quiz"}))
@NamedQuery(
        name = "Summary.findByUserAndQuiz",
        query = "FROM Summary " +
                "WHERE user = :user AND quiz = :quiz"
)
public class Summary extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;

    @NotNull
    private int currentScore;

    @NotNull
    private int bestScore;

    @OneToMany
    @JoinColumn(name = "fk_question")
    private List<Question> wrongQuestions;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_quiz")
    private Quiz quiz;

    public Summary() {
        wrongQuestions = new ArrayList<>();
    }

    public boolean isNew() {
        return getId() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Summary)) {
            return false;
        }
        Summary that = (Summary) obj;

        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
