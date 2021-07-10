package pl.kuba;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kuba.domain.RentalCompanyService;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.RentalCompanyRepository;

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
    void configureRentalCompanyShouldBeNotNull() {
        //given
        String name = "x";
        String website = "z";
        String contactAddress = "123 xd";
        String owner = "asd asd";
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        when(rentalCompanyRepository.save(rentalCompany)).thenReturn(rentalCompany);

        //when
      RentalCompany rentalCompany1 = rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner);

        //then
        Assertions.assertNotNull(rentalCompany1);
        Assertions.assertEquals("x",rentalCompany1.getName());
        Assertions.assertEquals("z",rentalCompany1.getWebsite());
        Assertions.assertEquals("123 xd",rentalCompany1.getContactAddress());
        Assertions.assertEquals("asd asd",rentalCompany1.getOwner());
        System.out.println(rentalCompany1);
    }

}
