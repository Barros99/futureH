package com.future8.droneapp.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "deliveries")
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "drone_id")
  @OneToOne(fetch = FetchType.LAZY)
  private Drone drone;

  private Byte[] video;
  private LocalDate deliveredDate;
  private LocalDate releasedDate;
  private String destiny;
  private String origin;
  private String status;

  public Delivery() {}

  /**
   * Method to create a new delivery.
   */
  public Delivery(
      Drone drone,
      Byte[] video,
      LocalDate deliveredDate,
      LocalDate releasedDate,
      String destiny,
      String origin,
      String status) {
    this.drone = drone;
    this.video = video;
    this.deliveredDate = deliveredDate;
    this.releasedDate = releasedDate;
    this.destiny = destiny;
    this.origin = origin;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

  public Byte[] getVideo() {
    return video;
  }

  public void setVideo(Byte[] video) {
    this.video = video;
  }

  public LocalDate getDeliveredDate() {
    return deliveredDate;
  }

  public void setDeliveredDate(LocalDate deliveredDate) {
    this.deliveredDate = deliveredDate;
  }

  public LocalDate getReleasedDate() {
    return releasedDate;
  }

  public void setReleasedDate(LocalDate releasedDate) {
    this.releasedDate = releasedDate;
  }

  public String getDestiny() {
    return destiny;
  }

  public void setDestiny(String destiny) {
    this.destiny = destiny;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
}
