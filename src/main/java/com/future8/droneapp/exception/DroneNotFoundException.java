package com.future8.droneapp.exception;

public class DroneNotFoundException extends RuntimeException {

  public DroneNotFoundException() {

    super("Drone not found!");
  }
}
