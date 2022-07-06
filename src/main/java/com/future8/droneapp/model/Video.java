package com.future8.droneapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JoinColumn(name = "delivery_id")
  @OneToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Delivery delivery;

  private String name;
  private String url;
  private String path;

  public Video() {}

  public Video(String name, String url) {
    this.name = name;
    this.url = url;
  }

  /**
   * Method create a video object.
   */
  public Video(String name, String url, String path) {
    this.name = name;
    this.url = url;
    this.path = path;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Delivery getDelivery() {
    return delivery;
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
