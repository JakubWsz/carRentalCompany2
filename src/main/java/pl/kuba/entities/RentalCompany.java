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

    public RentalCompany(String name, String website, String contactAddress, String owner) {
        this.name = name;
        this.website = website;
        this.contactAddress = contactAddress;
        this.owner = owner;
    }

    public RentalCompany() {

    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public String getOwner() {
        return owner;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
