package com.future8.droneapp.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  /** Handle UnexpectedException. */
  @ExceptionHandler(UnexpectedException.class)
  @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
  public Object handleUnexpectedException(UnexpectedException exception) {

    Map<String, Object> body =
        new HashMap<>() {
          {
            put("message", exception.getMessage());
          }
        };

    return body;
  }

  /** Handle DroneNotFoundException. */
  @ExceptionHandler(DroneNotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public Object handleDroneNotFoundException(DroneNotFoundException exception) {

    Map<String, Object> body =
        new HashMap<>() {
          {
            put("message", exception.getMessage());
          }
        };

    return body;
  }
}
