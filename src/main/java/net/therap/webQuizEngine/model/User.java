package net.therap.webQuizEngine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static jakarta.persistence.TemporalType.DATE;
import static java.util.Objects.isNull;
import static net.therap.webQuizEngine.model.Role.*;

/**
 * @author adnan
 * @since 7/16/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`user`")
@NamedQueries({
        @NamedQuery(
                name = "User.findAll",
                query = "FROM User"
        ),
        @NamedQuery(
                name = "User.findByUsername",
                query = "FROM User " +
                        "WHERE username = :username"
        ),
        @NamedQuery(
                name = "User.findByEmail",
                query = "FROM User " +
                        "WHERE email = :email"
        ),
        @NamedQuery(
                name = "User.findByPassword",
                query = "FROM User " +
                        "WHERE password = :password"
        )
})
public class User extends Persistent {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String username;

    @Email()
    private String email;

    @Temporal(value = DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private long age;

    @NotNull
    @Size(min = 5, message = "{error.size.user.password}")
    private String password;

    @Enumerated(STRING)
    private Role role;

    public boolean isNew() {
        return isNull(getId()) || getId() == 0;
    }

    public boolean isAdmin() {
        return Objects.nonNull(getRole()) && getRole().equals(ADMIN);
    }

    public boolean isTeacher() {
        return Objects.nonNull(getRole()) && getRole().equals(TEACHER);
    }

    public boolean isStudent() {
        return Objects.nonNull(getRole()) && getRole().equals(STUDENT);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User that)) {
            return false;
        }

        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
