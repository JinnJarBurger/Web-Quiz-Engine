package net.therap.webQuizEngine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static java.util.Objects.isNull;

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

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

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
        return isNull(getId()) || getId() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Question that)) {
            return false;
        }

        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
