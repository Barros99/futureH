package com.future8.droneapp.repository;

import com.future8.droneapp.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Integer> {}
