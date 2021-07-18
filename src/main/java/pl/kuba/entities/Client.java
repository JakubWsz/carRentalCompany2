package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@AllArgsConstructor
@Entity
@Setter
public class Client extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    public Client() {

    }
}
