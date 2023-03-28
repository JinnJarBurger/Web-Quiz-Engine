package net.therap.webQuizEngine.model;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.FetchMode.SELECT;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "quiz")
@NamedQueries({
        @NamedQuery(
                name = "Quiz.findAll",
                query = "FROM Quiz " +
                        "WHERE questions.size > 0 " +
                        "ORDER BY created DESC"
        ),
        @NamedQuery(
                name = "Quiz.findAllByCategory",
                query = "FROM Quiz " +
                        "WHERE category = :category " +
                        "AND questions.size > 0 " +
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
