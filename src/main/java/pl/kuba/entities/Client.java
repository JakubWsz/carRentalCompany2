package pl.kuba.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class Client extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
