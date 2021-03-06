package pl.kuba.domain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.domain.repository.TestBranchStore;
import pl.kuba.domain.repository.TestRentalCompanyStore;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalCompanyServiceTests {

    @Test
    void configureRentalCompanyDataShouldBeNotNull() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        testRentalCompanyStore.save(rentalCompany);
        //when
        RentalCompany rentalCompany1 = rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner);

        //then
        Assertions.assertNotNull(rentalCompany1);
        Assertions.assertEquals("x", rentalCompany1.getName());
        Assertions.assertEquals("https://www.something.com", rentalCompany1.getWebsite());
        Assertions.assertEquals("123 xd", rentalCompany1.getContactAddress());
        Assertions.assertEquals("asd asd", rentalCompany1.getOwner());
    }

    @Test
    void configureRentalCompanyWithNullNameShouldThrowException() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = null;
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        //when

        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));
        //then

        Assertions.assertEquals("Name is null",runtimeException.getMessage());
    }

    @Test
    void configureRentalCompanyWithWrongWebsiteUrlShouldThrowException() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = "asd";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));
        //then
        Assertions.assertEquals("URL is invalid", runtimeException.getMessage());
    }

    @Test
    void configureRentalCompanyWithNullWebsiteShouldThrowException() {
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = null;
        String contactAddress = "123 xd";
        String owner = "asd asd";
        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));
        //then
        Assertions.assertEquals("Website is null", runtimeException.getMessage());
    }
    @Test
    void configureRentalCompanyWithNullContactAddressShouldThrowException() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = null;
        String owner = "asd asd";
        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));

        //then
        Assertions.assertEquals("Contact address is null", runtimeException.getMessage());
    }

    @Test
    void configureRentalCompanyWithNullOwnerShouldThrowException() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = null;

        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));

        //then
        Assertions.assertEquals("Owner is null", runtimeException.getMessage());
    }

    @Test
    void updateRentalCompanyShouldBeNotNullAndHaveDifferentValuesThanConfigureBranch() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        testRentalCompanyStore.save(rentalCompany);

        String newWebsiteName = "https://www.somethingNew.com";
        String newContactAddress = "321 dx";
        String newOwner = "qwe qwe";
        String newName = "y";
        RentalCompany rentalCompany1 = new RentalCompany(newOwner, newWebsiteName, newContactAddress, newOwner);
        testRentalCompanyStore.save(rentalCompany1);

        //when
        rentalCompanyService.updateRentalCompany(rentalCompany.getName(), rentalCompany1.getName(),
                rentalCompany1.getWebsite(), rentalCompany1.getContactAddress(),
                rentalCompany1.getOwner());

        //then
        Assertions.assertNotNull(rentalCompany1);
        Assertions.assertNotEquals(name, newName);
        Assertions.assertNotEquals(website, newWebsiteName);
        Assertions.assertNotEquals(contactAddress, newContactAddress);
        Assertions.assertNotEquals(owner, newOwner);
    }

    @Test
    void openNewBranchShouldBeNotNull() {
        //given
        TestBranchStore testBranchStore = new TestBranchStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(null,
                testBranchStore);
        String contactAddress = null;


        //when
        Branch branch1 = (rentalCompanyService.openNewBranch(contactAddress));

        //then
        Assertions.assertNotNull(branch1);
    }

    @Test
    void closeBranchShouldReturnBranchIsClosed() {
        //given
        TestBranchStore testBranchStore = new TestBranchStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(null,
                testBranchStore);
        String contactAddress = "Szkolna 17";
        Branch branch = new Branch(contactAddress);
        testBranchStore.save(branch);

        //when
        boolean isClosed = (rentalCompanyService.closeBranch(branch.getAddress()));

        //then
        Assertions.assertTrue(isClosed);
    }
}