package pl.kuba.api.request;

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
}
