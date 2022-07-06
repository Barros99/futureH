package com.future8.droneapp.dto;

import com.future8.droneapp.model.Video;
import java.util.ArrayList;
import java.util.List;

public class VideoDto {

  private Integer id;
  private String name;
  private String path;

  public VideoDto() {}

  /** Method to create a videoDto object. */
  public VideoDto(Integer id, String name, String path) {
    this.id = id;
    this.name = name;
    this.path = path;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  /** Method to convert a video object to a videoDto object. */
  public static List<VideoDto> convert(List<Video> videos) {
    List<VideoDto> videosDto = new ArrayList<>();
    for (Video obj : videos) {
      VideoDto dto = new VideoDto();
      dto.setId(obj.getId());
      dto.setName(obj.getName());
      dto.setPath(obj.getPath());
      videosDto.add(dto);
    }
    return videosDto;
  }
}
