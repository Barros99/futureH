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
  public Delivery create(Integer droneId, Delivery deliveryRequest) {
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny(deliveryRequest.getDestiny());
    delivery.setStatus(deliveryRequest.getStatus());

    droneRepository
        .findById(droneId)
        .ifPresent(
            drone -> {
              delivery.setDrone(drone);
            });
    deliveryRepository.save(delivery);
    return delivery;
  }

  /** Get all deliveries. */
  public List<Delivery> getDeliveries() {
    return deliveryRepository.findAll();
  }

  /** Update a delivery. */
  public Delivery update(Integer id, Delivery delivery) {
    Delivery deliveryToUpdate = deliveryRepository.findById(id).orElse(null);
    if (deliveryToUpdate != null) {
      deliveryToUpdate.setDestiny(delivery.getDestiny());
      deliveryToUpdate.setStatus(delivery.getStatus());
      deliveryRepository.save(deliveryToUpdate);
    }
    return deliveryToUpdate;
  }

  /** Delete a delivery. */
  public void delete(Integer id) {
    deliveryRepository.deleteById(id);
  }
}
