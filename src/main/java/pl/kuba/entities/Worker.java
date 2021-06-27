package pl.kuba.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Embeddable
public class Worker extends BaseEntity {
    private String firstname;
    private String lastname;
    private String position;
    @Embedded
    private Branch branch;
}
