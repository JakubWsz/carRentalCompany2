package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Return extends BaseEntity{
    @ManyToOne
    private Worker worker;
    private LocalDate returnDate;
    @ManyToOne
    private Reservation reservation;
    private int surcharge;
    private String comment;

    public Return() {

    }
}
