package net.therap.webQuizEngine.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

import static java.util.Objects.isNull;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author adnan
 * @since 7/20/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "history")
@NamedQuery(
        name = "History.findAllByUser",
        query = "FROM History " +
                "WHERE summaryLog.user = :user " +
                "AND answer.user = :user " +
                "ORDER BY created DESC "
)
public class History extends Persistent {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private int score;

    @ManyToOne
    @JoinColumn(name = "fk_summary")
    private Summary summaryLog;

    @ManyToOne
    @JoinColumn(name = "fk_answer")
    private Answer answer;

    public boolean isNew() {
        return isNull(getId()) || getId() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof History that)) {
            return false;
        }

        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
