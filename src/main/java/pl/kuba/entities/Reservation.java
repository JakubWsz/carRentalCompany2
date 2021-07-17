package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@Entity
public class Reservation extends BaseEntity {
    private Date reservationDate;
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
}
