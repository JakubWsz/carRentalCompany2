package pl.kuba.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Rent extends BaseEntity{
    @Embedded
    private Worker worker;
    private Date rentDate;
    @Embedded
    private Reservation reservation;
    private String comment;
}
