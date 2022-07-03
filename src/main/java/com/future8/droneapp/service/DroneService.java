package com.future8.droneapp.service;

import com.future8.droneapp.exception.DroneNotFoundException;
import com.future8.droneapp.model.Drone;
import com.future8.droneapp.repository.DroneRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

  @Autowired private DroneRepository droneRepository;

  public Drone create(Drone drone) {
    return droneRepository.save(drone);
  }

  /** Get all drones. */
  public List<Drone> getAllDrones() {
    List<Drone> drones = droneRepository.findAll();
    if (drones.isEmpty()) {
      return Collections.<Drone>emptyList();
    }
    return drones;
  }

  /**
   * Update a drone.
   */
  public Drone update(Integer id, Drone droneRequest) {

    Optional<Drone> optional = droneRepository.findById(id);

    if (optional.isPresent()) {
      Drone drone = optional.get();
      drone.setLatitude(droneRequest.getLatitude());
      drone.setLongitude(droneRequest.getLongitude());
      droneRepository.save(drone);
      return drone;

    } else {
      throw new DroneNotFoundException();
    }
  }

  /**
   * Delete a drone.
   */
  public void delete(Integer id) {
    if (droneRepository.existsById(id)) {
      droneRepository.deleteById(id);
    } else {
      throw new DroneNotFoundException();
    }
  }
}
