package com.future8.droneapp.controlller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.future8.droneapp.model.Drone;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class DroneController {
  @Test
  @DisplayName("1 - Drone can be created with sucess")
  public void create() {
    Drone drone = new Drone(15151, 46545);
    given().body(drone).contentType(ContentType.JSON).post("/drone").then().statusCode(201);
  }

  @Test
  @DisplayName("2 - List all drones")
  public void ListAll() {
    Drone drone = new Drone(15151, 46545);
    Drone drone2 = new Drone(2020, 2020);
    Drone drone3 = new Drone(1010, 1010);

    given().body(drone).contentType(ContentType.JSON).post("/drone");
    given().body(drone2).contentType(ContentType.JSON).post("/drone");
    given().body(drone3).contentType(ContentType.JSON).post("/drone");

    Response response = given().when().get("/drone");
    assertTrue(response.asString().length() > 100);
  }

  @Test
  @DisplayName("3 - Drone can be edited with sucess")
  public void findById() {
    Drone drone = new Drone(15151, 46545);

    given().body(drone).contentType(ContentType.JSON).post("/drone");
    drone.setLatitude(2020);
    given().body(drone).contentType(ContentType.JSON).put("/drone/1").then().statusCode(200);
  }

  @Test
  @DisplayName("4 - Drone can be edited with sucess")
  public void delete() {
    Drone drone = new Drone(15151, 46545);

    given().body(drone).contentType(ContentType.JSON).post("/drone");
    given().delete("/drone/1").then().statusCode(200);
  }
}
