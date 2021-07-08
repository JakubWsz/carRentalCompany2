package pl.kuba.api.request.rentalcompany;

import lombok.Getter;

@Getter
public class RentalCompanyConfigRequest {
   private final String name;
   private final String website;
   private final String contactAddress;
   private final String owner;

    public RentalCompanyConfigRequest(String name, String website, String contactAddress, String owner) {
        this.name = name;
        this.website = website;
        this.contactAddress = contactAddress;
        this.owner = owner;
    }
}
