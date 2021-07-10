package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
public class Reservation extends BaseEntity{
    private Date reservationDate;
    @OneToOne
    private Client client;
    @OneToOne
    private Car car;
    private Date rentDate;
    private Date returnDate;
    @OneToOne
    private Branch rentingBranch;
    @OneToOne
    private Branch receivingBranch;
    private BigDecimal amountToPay;

    public Reservation(Date reservationDate, Client client, Car car, Date rentDate, Date returnDate,
                       Branch rentingBranch, Branch receivingBranch, BigDecimal amountToPay) {
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
