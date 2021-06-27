package pl.kuba.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
