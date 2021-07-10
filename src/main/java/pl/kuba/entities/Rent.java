package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Rent extends BaseEntity{
    @ManyToOne
    private Worker worker;
    private Date rentDate;
    @ManyToOne
    private Reservation reservation;
    private String comment;
}
