package com.future8.droneapp.controlller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.future8.droneapp.model.Delivery;
import com.future8.droneapp.model.Drone;
import com.future8.droneapp.repository.DeliveryRepository;
import com.future8.droneapp.repository.DroneRepository;
import java.time.LocalDate;
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
public class DeliveryTest {
  @Autowired private MockMvc mockMvc;

  @SpyBean private DeliveryRepository deliveryRepository;
  @SpyBean private DroneRepository droneRepository;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void initEach() {
    deliveryRepository.deleteAll();
  }

  @Test
  @DisplayName("1 - Delivery can be created with sucess")
  public void create() throws JsonProcessingException, Exception {
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny("destiny");
    delivery.setStatus("status");

    Drone drone = new Drone(15151, 46545);

    droneRepository.save(drone);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/delivery/{id}", drone.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(delivery)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @DisplayName("2 - List all deliveries")
  public void listAll() throws Exception {
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny("destiny");
    delivery.setStatus("status");

    Drone drone = new Drone(15151, 46545);

    droneRepository.save(drone);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/delivery").contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @DisplayName("3 - Delivery can be edited with sucess")
  public void update() throws Exception {
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny("destiny");
    delivery.setStatus("status");

    Drone drone = new Drone(15151, 46545);

    droneRepository.save(drone);
    deliveryRepository.save(delivery);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/delivery/{id}", delivery.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(delivery)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @DisplayName("4 - Delivery can be deleted with sucess")
  public void delete() throws Exception {
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny("destiny");
    delivery.setStatus("status");

    Drone drone = new Drone(15151, 46545);

    droneRepository.save(drone);
    deliveryRepository.save(delivery);

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/delivery/{id}", delivery.getId())
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
