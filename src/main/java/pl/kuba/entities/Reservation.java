package pl.kuba.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Embeddable
public class Reservation extends BaseEntity{
    private Date reservationDate;
    @Embedded
    private Client client;
    @Embedded
    private Car car;
    private Date rentDate;
    private Date returnDate;
    @Embedded
    private Branch rentingBranch;
    @Embedded
    private Branch receivingBranch;
    private int amountToPay;
}
