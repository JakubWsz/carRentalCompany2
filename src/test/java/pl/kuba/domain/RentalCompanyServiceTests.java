package pl.kuba.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.domain.servises.RentalCompanyService;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.domain.stores.RentalCompanyStore;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        String name = "company";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        testRentalCompanyStore.save(rentalCompany);
        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(null, website, contactAddress, owner));
        //then
        Assertions.assertEquals("Name is null", runtimeException.getMessage());
    }

    @Test
    void configureRentalCompanyWithNullWebsiteShouldThrowException() {
        //given
        TestRentalCompanyStore testRentalCompanyStore = new TestRentalCompanyStore();
        RentalCompanyService rentalCompanyService = new RentalCompanyService(testRentalCompanyStore, null);
        String name = "x";
        String website = null;
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        testRentalCompanyStore.save(rentalCompany);
        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));
        //then
        Assertions.assertEquals("URL is invalid", runtimeException.getMessage());
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
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        testRentalCompanyStore.save(rentalCompany);
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
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        testRentalCompanyStore.save(rentalCompany);
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
        Branch branch = new Branch(contactAddress);
        testBranchStore.save(branch);

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

class TestRentalCompanyStore implements RentalCompanyStore {
    private final List<RentalCompany> rentalCompanies = new ArrayList<>();

    @Override
    public Optional<RentalCompany> findByName(String name) {
        return rentalCompanies.stream()
                .filter(rentalCompany -> rentalCompany.equals(name))
                .findFirst();
    }

    @Override
    public RentalCompany save(RentalCompany rentalCompany) {
        rentalCompanies.add(rentalCompany);
        return rentalCompany;
    }
}

class TestBranchStore implements BranchStore {
    private final List<Branch> branches = new ArrayList<>();

    @Override
    public List<Branch> findAll() {
        return new ArrayList<>(branches);
    }

    @Override
    public Branch save(Branch branch) {
        branches.add(branch);
        return branch;
    }

    @Override
    public Optional<Branch> findByAddress(String contactAddress) {
        return branches.stream()
                .filter(branch -> branch.getAddress().equals(contactAddress))
                .findFirst();
    }
}