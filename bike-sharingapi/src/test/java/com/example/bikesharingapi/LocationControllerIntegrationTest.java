package com.example.bikesharingapi;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.models.Location;
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
public class LocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void whenGetById_thenReturnBicycle() throws Exception {
        Location location = new Location();
        location.setLocationId(UUID.randomUUID());

        locationRepository.save(location);

        this.mockMvc.perform(get("/location/" + location.getLocationId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"));

        locationRepository.delete(location);
    }

    @Test
    public void whenDeleteById_thenReturnNone() throws Exception {
        Location location = new Location();
        location.setLocationId(UUID.randomUUID());


        locationRepository.save(location);

        this.mockMvc.perform(delete("/location/" + location.getLocationId()))
                .andExpect(status().is(200));

        assert locationRepository.getByLocationId(location.getLocationId()) == null;
    }

    @Test
    public void whenUpdateById_thenReturnsUpdated() throws Exception {
        Location location = new Location();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        location.setLocationId(UUID.randomUUID());
        location.setRadius(10);

        locationRepository.saveAndFlush(location);

        location.setRadius(5);

        this.mockMvc.perform(put("/location/").contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(location)))
                .andExpect(status().is(200));

        assert locationRepository.getByLocationId(location.getLocationId()).getRadius() == location.getRadius();
    }

    @Test
    public void whenBicycleAdded_thenReturnAddedBicycleById() throws Exception {
        Location location = new Location();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        location.setLocationId(UUID.randomUUID());


        this.mockMvc.perform(post("/location/").contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(location)))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        assert locationRepository.getByLocationId(location.getLocationId()).getLocationId().toString().equals(location.getLocationId().toString());
    }
}
