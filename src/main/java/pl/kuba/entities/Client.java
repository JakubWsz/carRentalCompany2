package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Client extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
