package pl.kuba.entities;

import javax.persistence.*;

@Entity
public class Worker extends BaseEntity{
    private String firstname;
    private String lastname;
    private boolean manager;
    @OneToOne
    private Branch branch;

    public Worker(String firstname, String lastname, boolean manager, Branch branch) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.manager = manager;
        this.branch = branch;
    }

    public Worker() {

    }
}
