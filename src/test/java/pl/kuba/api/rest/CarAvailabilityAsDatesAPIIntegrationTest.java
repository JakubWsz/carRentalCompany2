package pl.kuba.api.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kuba.domain.stores.RentStore;
import pl.kuba.infrastructure.persistence.CarRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CarAvailabilityAsDatesAPIIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentStore rentStore;

    @Test
    void getCarAvailabilityStatusByParticularDate() throws Exception {
        System.out.println("dostępne auta:" + carRepository.findAll());
        System.out.println("dostępne rezerwacje:" + rentStore.findAll());

        mvc.perform(MockMvcRequestBuilders.get("/car-availability/status")
                        .param("id", "2")
                        .param("date", "15-08-1999"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }
}