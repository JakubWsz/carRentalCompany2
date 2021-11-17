package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Reservation extends BaseEntity {
    private LocalDate reservationDate;
    @OneToOne
    private Client client;
    @OneToOne
    private Car car;
    private LocalDate rentDate;
    private LocalDate returnDate;
    @ManyToOne
    private Branch rentingBranch;
    @ManyToOne
    private Branch receivingBranch;
    private BigDecimal amountToPay;

    public Reservation() {

    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationDate=" + reservationDate +
                ", client=" + client +
                ", car=" + car +
                ", rentDate=" + rentDate +
                ", returnDate=" + returnDate +
                ", rentingBranch=" + rentingBranch +
                ", receivingBranch=" + receivingBranch +
                ", amountToPay=" + amountToPay +
                '}';
    }
}
