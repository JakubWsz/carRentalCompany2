package pl.kuba.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Optional;

@Entity
public class Return extends BaseEntity{
    @Embedded
    private Worker worker;
    private Date returnDate;
    @Embedded
    private Reservation reservation;
    private int surcharge;
    private String comment;
}
