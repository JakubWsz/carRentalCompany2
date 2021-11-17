package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Rent extends BaseEntity{
    @ManyToOne
    private Worker worker;
    private LocalDate rentDate;
    @ManyToOne
    private Reservation reservation;
    private String comment;

    public Rent() {

    }

    @Override
    public String toString() {
        return "Rent{" +
                "worker=" + worker +
                ", rentDate=" + rentDate +
                ", reservation=" + reservation +
                ", comment='" + comment + '\'' +
                '}';
    }
}
