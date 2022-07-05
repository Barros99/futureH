package com.future8.droneapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DroneDeliveryApplication {

  public static void main(String[] args) {
    SpringApplication.run(DroneDeliveryApplication.class, args);
  }
}
