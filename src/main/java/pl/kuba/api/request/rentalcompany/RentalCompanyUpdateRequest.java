package pl.kuba.api.request.rentalcompany;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RentalCompanyUpdateRequest {
   private final String finByThisName;
   private final String newWebsiteName;
   private final String newContactAddress;
   private final String newOwner;
   private final String newName;
}
