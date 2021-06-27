package pl.kuba.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Embeddable
public class Branch extends BaseEntity {
    private String address;
    @OneToMany
    private List<Worker> workers;
    @ManyToMany
    private List<Car> availableCars;
}
