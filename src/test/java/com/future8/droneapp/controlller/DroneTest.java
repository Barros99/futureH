package com.future8.droneapp.controlller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.future8.droneapp.model.Drone;
import com.future8.droneapp.repository.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DroneTest {
  @Autowired private MockMvc mockMvc;

  @SpyBean private DroneRepository droneRepository;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void initEach() {
    droneRepository.deleteAll();
  }

  @Test
  @DisplayName("1 - Drone can be created with sucess")
  public void create() throws JsonProcessingException, Exception {
    Drone drone = new Drone(15151, 46545);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/drone")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(drone)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @DisplayName("2 - List all drones")
  public void listAll() throws Exception {
    Drone drone = new Drone(15151, 46545);
    Drone drone2 = new Drone(2020, 2020);

    droneRepository.save(drone);
    droneRepository.save(drone2);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/drone").contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(drone.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(drone2.getId()));
  }

  @Test
  @DisplayName("3 - Drone can be edited with sucess")
  public void update() throws Exception {
    Drone drone = new Drone(15151, 46545);
    droneRepository.save(drone);

    drone.setLatitude(2020);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/drone/{id}", drone.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(drone)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void delete() throws Exception {
    Drone drone = new Drone(15151, 46545);
    droneRepository.save(drone);

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/drone/{id}", drone.getId())
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
