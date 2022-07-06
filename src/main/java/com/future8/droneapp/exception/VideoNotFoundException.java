package com.future8.droneapp.exception;

public class VideoNotFoundException extends VideoException {

  public VideoNotFoundException(String message) {
    super(message);
  }

  public VideoNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
