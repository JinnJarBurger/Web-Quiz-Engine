package net.therap.webQuizEngine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author adnan
 * @since 7/16/2022
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Persistent implements Serializable {

    @Temporal(value = TIMESTAMP)
    @Column(name = "created_on", nullable = false, updatable = false)
    protected Date created;

    @Temporal(value = TIMESTAMP)
    @Column(name = "updated_on")
    protected Date updated;

    @PrePersist
    protected void setCreated() {
        created = new Date();
        updated = new Date();
    }

    @PreUpdate
    protected void setUpdated() {
        updated = new Date();
    }
}
