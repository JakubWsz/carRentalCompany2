package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Revenue extends BaseEntity{
    private long amountSum;
}
