package com.example.bikesharingapi;

import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.models.State;
import com.example.bikesharingapi.repository.StateRepository;
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
public class StateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void whenGetById_thenReturnBicycle() throws Exception {
        State state = new State();
        state.setStateId(UUID.randomUUID());

        stateRepository.save(state);

        this.mockMvc.perform(get("/state/" + state.getStateId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"));

        stateRepository.delete(state);
    }

    @Test
    public void whenDeleteById_thenReturnNone() throws Exception {
        State state = new State();
        state.setStateId(UUID.randomUUID());


        stateRepository.save(state);

        this.mockMvc.perform(delete("/state/" + state.getStateId()))
                .andExpect(status().is(200));

        assert stateRepository.getByStateId(state.getStateId()) == null;
    }

    @Test
    public void whenUpdateById_thenReturnsUpdated() throws Exception {
        State state = new State();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        state.setStateId(UUID.randomUUID());
        state.setName("before");

        stateRepository.saveAndFlush(state);

        state.setName("after");

        this.mockMvc.perform(put("/state/").contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(state)))
                .andExpect(status().is(200));

        assert stateRepository.getByStateId(state.getStateId()).getName().equals(state.getName());
    }

    @Test
    public void whenBicycleAdded_thenReturnAddedBicycleById() throws Exception {
        State state = new State();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        state.setStateId(UUID.randomUUID());


        this.mockMvc.perform(post("/state/").contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(state)))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        assert stateRepository.getByStateId(state.getStateId()).getStateId().toString().equals(state.getStateId().toString());
    }
}
