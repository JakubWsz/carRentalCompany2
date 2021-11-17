package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
