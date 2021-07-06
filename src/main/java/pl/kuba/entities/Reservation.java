package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
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

    public Reservation(Date reservationDate, Client client, Car car, Date rentDate, Date returnDate,
                       Branch rentingBranch, Branch receivingBranch, int amountToPay) {
        this.reservationDate = reservationDate;
        this.client = client;
        this.car = car;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.rentingBranch = rentingBranch;
        this.receivingBranch = receivingBranch;
        this.amountToPay = amountToPay;
    }

    public Reservation() {

    }
}
