package pl.kuba.api.dto.request.rentalcompany;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RentalCompanyUpdateRequest {
   private final String oldName;
   private final String newWebsiteName;
   private final String newContactAddress;
   private final String newOwner;
   private final String newName;
}
