package pl.kuba.api.rest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kuba.domain.services.CarService;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CarAPITest {

    @Autowired
    private MockMvc mvc;

    @MockBean(answer = Answers.CALLS_REAL_METHODS)
    private CarService service;
    
    List<Car>cars = new ArrayList<>();
    Car car = new Car("sf","ad","awd", BodyType.HATCHBACK,321,"a",123,
            AvailabilityStatus.AVAILABLE, BigDecimal.TEN);
    private void addCars(){
        cars.add(car);
    }

    @Before(value = "branch")
    public void setUp(){
        addCars();
        when(service.getAvailableCars("branch"))
                .thenReturn(cars);
    }

    @Test
    void getCarAvailable() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/car/available").param("branch"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}