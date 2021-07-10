package pl.kuba.api.request.rentalcompany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RentalCompanyConfigRequest {
   private final String name;
   private final String website;
   private final String contactAddress;
   private final String owner;
}