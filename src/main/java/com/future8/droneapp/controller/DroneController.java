package com.future8.droneapp.controller;

import com.future8.droneapp.model.Drone;
import com.future8.droneapp.service.DroneService;
import java.util.ArrayList;
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
@RequestMapping("/drone")
public class DroneController {

  @Autowired private DroneService droneService;

  /** Create a drone. */
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Drone drone(@RequestBody Drone drone) {
    return droneService.create(drone);
  }

  /** Get all drones. */
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<Drone> getAllDrones() {
    return new ArrayList<>(droneService.getAllDrones());
  }

  /** Update a drone. */
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Drone update(@PathVariable(value = "id") Integer id, @RequestBody Drone drone) {
    return droneService.update(id, drone);
  }

  /** Delete a drone. */
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void delete(@PathVariable(value = "id") Integer id) {
    droneService.delete(id);
  }
}
