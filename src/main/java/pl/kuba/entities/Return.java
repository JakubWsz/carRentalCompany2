package pl.kuba.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Return extends BaseEntity{
    @ManyToOne
    private Worker worker;
    private Date returnDate;
    @ManyToOne
    private Reservation reservation;
    private int surcharge;
    private String comment;
}
