package com.future8.droneapp.controller;

import com.future8.droneapp.model.Delivery;
import com.future8.droneapp.service.DeliveryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

  @Autowired private DeliveryService deliveryService;

  @PostMapping("/{drone}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Object create(
      @PathVariable("drone") Integer drone, @RequestBody Delivery deliveryRequest) {
    return deliveryService.create(drone, deliveryRequest);
  }

  @GetMapping
  public List<Delivery> getDeliveries() {
    return deliveryService.getDeliveries();
  }

  /** Update a drone. */
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Delivery update(@PathVariable(value = "id") Integer id, @RequestBody Delivery delivery) {
    return deliveryService.update(id, delivery);
  }

  /** Delete a drone. */
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void delete(@PathVariable(value = "id") Integer id) {
    deliveryService.delete(id);
  }

}
