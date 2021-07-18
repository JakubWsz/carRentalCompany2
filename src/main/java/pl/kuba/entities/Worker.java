package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Worker extends BaseEntity{
    private String firstname;
    private String lastname;
    private boolean manager;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Branch branch;

    public Worker() {

    }
}
