package com.future8.droneapp.service;

import com.future8.droneapp.model.Delivery;
import com.future8.droneapp.model.Drone;
import com.future8.droneapp.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverService {

  @Autowired private DeliveryRepository deliveryRepository;

  public Delivery create(Drone droneId, Delivery delivery) {
    return deliveryRepository.save(delivery);
  }
}
