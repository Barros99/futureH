package com.future8.droneapp.config.video;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("video")
public class VideoProperties {

  /** Folder location for storing files. */
  private String location = "videos";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
