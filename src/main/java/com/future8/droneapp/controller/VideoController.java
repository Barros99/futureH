package com.future8.droneapp.controller;

import com.future8.droneapp.model.Video;
import com.future8.droneapp.service.VideoService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoController {

  @Autowired private VideoService videoService;

  /**
   * Creates a new video and saves it to the database.
   *
   * @param id The id of the delivery to which the video belongs.
   * @param video The video file.
   * @return The newly created video.
   * @throws IOException If the video file could not be read.
   */
  @PostMapping("/{id}")
  public Video uploadAndSave(
      @PathVariable("id") Integer id, @RequestParam("video") MultipartFile video)
      throws IOException {
    return videoService.uploadAndSave(id, video);
  }

  /**
   * Returns the video with the given id.
   *
   * @param id The id of the video to return.
   * @return The video with the given id.
   */
  @GetMapping("/{id}")
  public Video getVideoById(@PathVariable("id") Integer id) {
    return videoService.getVideo(id);
  }

  /**
   * Returns all videos.
   *
   * @return The videos.
   */
  @GetMapping
  public List<Video> getVideos() {
    return videoService.getVideos();
  }

  /**
   * Deletes the video with the given id.
   *
   * @param id The id of the video to delete.
   */
  @DeleteMapping("/{id}")
  public void deleteVideo(@PathVariable("id") Integer id) {
    videoService.deleteVideo(id);
  }
}
