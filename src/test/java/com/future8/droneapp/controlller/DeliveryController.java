package com.future8.droneapp.controlller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.future8.droneapp.model.Delivery;
import com.future8.droneapp.model.Drone;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class DeliveryController {
  @Test
  @DisplayName("1 - Delivery can be created with sucess")
  public void create() {
    Drone drone = new Drone(15151, 46545);
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny("destiny");
    delivery.setStatus("status");
    given().body(drone).contentType(ContentType.JSON).post("/drone");
    given().body(delivery).contentType(ContentType.JSON).post("/delivery/1").then().statusCode(201);
  }

  @Test
  @DisplayName("2 - List all deliveries")
  public void ListAll() {
    Response response = given().when().get("/delivery");
    assertTrue(response.asString().length() > 50);
  }

  @Test
  @DisplayName("3 - Delivery can be edited with sucess")
  public void edit() {
    Drone drone = new Drone(15151, 46545);
    Delivery delivery = new Delivery();
    delivery.setDeliveredDate(LocalDate.now());
    delivery.setDestiny("destiny");
    delivery.setStatus("status");
    given().body(drone).contentType(ContentType.JSON).put("/delivery/1").then().statusCode(200);
  }

  @Test
  @DisplayName("4 - Delivery can be deleted with sucess")
  public void delete() {

    given().delete("/delivery/1").then().statusCode(200);
  }
}
