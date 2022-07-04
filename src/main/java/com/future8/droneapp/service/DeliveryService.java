package com.future8.droneapp.service;

import com.future8.droneapp.model.Delivery;
import com.future8.droneapp.repository.DeliveryRepository;
import com.future8.droneapp.repository.DroneRepository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

  @Autowired private DeliveryRepository deliveryRepository;
  @Autowired private DroneRepository droneRepository;

  /** Create a new delivery. */
  public Delivery create(Delivery deliveryRequest) {
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny(deliveryRequest.getDestiny());
    delivery.setStatus(deliveryRequest.getStatus());

    Integer lol = deliveryRequest.getDrone().getId();

    droneRepository
        .findById(deliveryRequest.getDrone().getId())
        .ifPresent(
            drone -> {
              delivery.setDrone(drone);
            });

    return deliveryRepository.save(delivery);
  }

  /** Get all deliveries. */
  public List<Delivery> getDeliveries() {
    return deliveryRepository.findAll();
  }
}
