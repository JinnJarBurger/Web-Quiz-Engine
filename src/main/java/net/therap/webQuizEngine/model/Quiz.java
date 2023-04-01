package net.therap.webQuizEngine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static java.util.Objects.isNull;
import static org.hibernate.annotations.FetchMode.SELECT;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "quiz")
@NamedQueries({
        @NamedQuery(
                name = "Quiz.findAll",
                query = "FROM Quiz " +
                        "WHERE questions IS NOT EMPTY " +
                        "ORDER BY created DESC"
        ),
        @NamedQuery(
                name = "Quiz.findAllByCategory",
                query = "FROM Quiz " +
                        "WHERE category = :category " +
                        "AND questions IS NOT EMPTY " +
                        "ORDER BY created DESC"
        ),
        @NamedQuery(
                name = "Quiz.findAllByUser",
                query = "FROM Quiz " +
                        "WHERE createdBy = :user " +
                        "ORDER BY created DESC"
        )
})
public class Quiz extends Persistent {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private String title;

    @NotNull
    @Size(min = 20, max = 1000)
    private String description;

    @NotNull
    @Enumerated(STRING)
    private Category category;

    @OneToMany(cascade = {MERGE, PERSIST},
            orphanRemoval = true,
            mappedBy = "quiz")
    @OrderBy("created DESC")
    @Fetch(SELECT)
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User createdBy;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public boolean isNew() {
        return isNull(getId()) || getId() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quiz that)) {
            return false;
        }

        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
