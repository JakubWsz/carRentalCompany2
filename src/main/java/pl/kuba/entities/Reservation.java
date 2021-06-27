package pl.kuba.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation extends BaseEntity{
    private Date reservationDate;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Car car;
    private Date rentDate;
    private Date returnDate;
    @ManyToOne
    private Branch rentingBranch;
    @ManyToOne
    private Branch receivingBranch;
    private int amountToPay;
}
