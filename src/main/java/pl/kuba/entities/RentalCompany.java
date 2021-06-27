package pl.kuba.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class RentalCompany extends BaseEntity {
    private String name;
    private String website;
    private String contactAddress;
    private String owner;
    @OneToMany
    private List<Branch> branches;
}
