package net.therap.webQuizEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author adnan
 * @since 7/16/2022
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "question")
@NamedQuery(
        name = "Question.findAllByQuiz",
        query = "FROM Question " +
                "WHERE quiz = :quiz " +
                "ORDER BY created DESC"
)
public class Question extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;

    @NotNull
    @Size(min = 10, message = "{error.size.question.quiz}")
    private String description;

    @ElementCollection
    @OrderColumn(name = "options_order")
    private List<String> options;

    @NotNull
    private String answer;

    @ManyToOne
    @JoinColumn(name = "fk_quiz", nullable = false)
    private Quiz quiz;

    public Question() {
        options = Arrays.asList(new String[4]);
    }

    public boolean isNew() {
        return getId() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Question)) {
            return false;
        }
        Question that = (Question) obj;

        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
