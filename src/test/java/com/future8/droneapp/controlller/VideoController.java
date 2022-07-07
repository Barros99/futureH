package com.future8.droneapp.controlller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class VideoController {
  @Test
  @DisplayName("2 - List all videos")
  public void ListAll() {
    Response response = given().when().get("TEXT_PLAIN_VALUE/video");
    assertTrue(response.asString().length() > 0);
  }
}
