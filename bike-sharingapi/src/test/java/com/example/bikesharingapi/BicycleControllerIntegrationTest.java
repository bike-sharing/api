package com.example.bikesharingapi;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import com.example.bikesharingapi.repository.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BicycleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BicycleRepository bicycleRepository;

    @Test
    public void whenGetById_thenReturnBicycle() throws Exception {
        Bicycle bicycle = new Bicycle();
        bicycle.setBicycleId(UUID.randomUUID());

        bicycleRepository.save(bicycle);

        this.mockMvc.perform(get("/bicycle/" + bicycle.getBicycleId())
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"));

        bicycleRepository.delete(bicycle);
    }

    @Test
    public void whenDeleteById_thenReturnNone() throws Exception {
        Bicycle bicycle = new Bicycle();
        bicycle.setBicycleId(UUID.randomUUID());

        bicycleRepository.save(bicycle);

        this.mockMvc.perform(delete("/bicycle/" + bicycle.getBicycleId()))
                .andExpect(status().is(200));

        assert bicycleRepository.getByBicycleId(bicycle.getBicycleId()) == null;
    }

    @Test
    public void whenUpdateById_thenReturnsUpdated() throws Exception {
        Bicycle bicycle = new Bicycle();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        bicycle.setBicycleId(UUID.randomUUID());
        bicycle.setAvailability(false);

        bicycleRepository.saveAndFlush(bicycle);

        bicycle.setAvailability(true);

        this.mockMvc.perform(put("/bicycle/").contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(bicycle)))
                .andExpect(status().is(200));

        assert bicycleRepository.getByBicycleId(bicycle.getBicycleId()).getAvailability() == bicycle.getAvailability();
    }

    @Test
    public void whenBicycleAdded_thenReturnAddedBicycleById() throws Exception {
        Bicycle bicycle = new Bicycle();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        bicycle.setBicycleId(UUID.randomUUID());


        this.mockMvc.perform(post("/bicycle/").contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(bicycle)))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        assert bicycleRepository.getByBicycleId(bicycle.getBicycleId()).getBicycleId().toString().equals(bicycle.getBicycleId().toString());
    }
}
