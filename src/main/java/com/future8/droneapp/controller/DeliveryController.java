package com.future8.droneapp.controller;

import com.future8.droneapp.model.Delivery;
import com.future8.droneapp.service.DeliveryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

  @Autowired private DeliveryService deliveryService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Delivery create(@RequestBody Delivery deliveryRequest) {
    return deliveryService.create(deliveryRequest);
  }

  @GetMapping
  public List<Delivery> getDeliveries() {
    return deliveryService.getDeliveries();
  }
}
