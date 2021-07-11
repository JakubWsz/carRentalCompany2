package pl.kuba;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kuba.domain.RentalCompanyService;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.RentalCompanyRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class RentalCompanyServiceTests {
    @InjectMocks
    private RentalCompanyService rentalCompanyService;
    @Mock
    private RentalCompanyRepository rentalCompanyRepository;
    @Mock
    private BranchRepository branchRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(rentalCompanyRepository);
        Mockito.reset(branchRepository);
        rentalCompanyService = Mockito.spy(new RentalCompanyService(rentalCompanyRepository, branchRepository));
    }

    @Test
    void configureRentalCompanyDataShouldBeNotNull() {
        //given
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);

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
        String name = null;
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);

        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));

        //then
        Assertions.assertEquals("Name is null", runtimeException.getMessage());
    }

    @Test
    void configureRentalCompanyWithNullWebsiteShouldThrowException() {
        //given
        String name = "x";
        String website = "wrong address";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);

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
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = null;
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);

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
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = null;
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);

        //when
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner));

        //then
        Assertions.assertEquals("Owner is null", runtimeException.getMessage());
    }

    @Test
    void updateBranchShouldBeNotNullAndHaveDifferentValuesThanConfigureBranch() {
        String name = "x";
        String website = "https://www.something.com";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        String newWebsiteName = "https://www.somethingNew.com";
        String newContactAddress = "321 dx";
        String newOwner = "qwe qwe";
        String newName = "y";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);
        when(rentalCompanyRepository.findByName(name)).thenReturn(java.util.Optional.of(rentalCompany));

        //when
        RentalCompany rentalCompany1 =
                rentalCompanyService.updateRentalCompany("x",newWebsiteName,newContactAddress,newOwner,newName);

        //then
        Assertions.assertNotNull(rentalCompany1);
        Assertions.assertNotEquals(name,newName);
        Assertions.assertNotEquals(website,newWebsiteName);
        Assertions.assertNotEquals(contactAddress,newContactAddress);
        Assertions.assertNotEquals(owner,newOwner);
    }

    @Test
    void openNewBranchShouldBeNotNull() {
        //given
        String contactAddress = "contactAddress";
        Branch branch = new Branch(contactAddress);
        when(branchRepository.save(branch)).thenReturn(branch);

        //when
        Branch branch1 = (rentalCompanyService.openNewBranch(contactAddress));

        //then
        Assertions.assertNotNull(branch1);
    }
}
