package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class RentalCompany extends BaseEntity {
    private String name;
    private String website;
    private String contactAddress;
    private String owner;
    @OneToMany
    private List<Branch> branches;

    public RentalCompany(String name, String website, String contactAddress, String owner) {
        this.name = name;
        this.website = website;
        this.contactAddress = contactAddress;
        this.owner = owner;
    }

    public RentalCompany() {

    }

    @Override
    public String toString() {
        return "RentalCompany{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", owner='" + owner + '\'' +
                ", branches=" + branches +
                '}';
    }
}
