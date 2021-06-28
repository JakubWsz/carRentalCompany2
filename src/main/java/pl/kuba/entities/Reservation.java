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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Branch getRentingBranch() {
        return rentingBranch;
    }

    public void setRentingBranch(Branch rentingBranch) {
        this.rentingBranch = rentingBranch;
    }

    public Branch getReceivingBranch() {
        return receivingBranch;
    }

    public void setReceivingBranch(Branch receivingBranch) {
        this.receivingBranch = receivingBranch;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(int amountToPay) {
        this.amountToPay = amountToPay;
    }
}
