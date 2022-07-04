package com.future8.droneapp.service;

import com.future8.droneapp.model.Video;
import com.future8.droneapp.repository.DeliveryRepository;
import com.future8.droneapp.repository.VideoRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

  @Autowired private VideoRepository videoRepository;
  @Autowired private DeliveryRepository deliveryRepository;

  /**
   * Creates a new video and saves it to the database.
   *
   * @param id The id to which the video belongs.
   * @param video The video file.
   * @return The newly created video.
   * @throws IOException If the video file could not be read.
   */
  public Video uploadAndSave(Integer id, MultipartFile video) throws IOException {

    String videoName = video.getOriginalFilename();
    Video videoDb = new Video(videoName, video.getBytes());

    deliveryRepository
        .findById(id)
        .ifPresent(
            delivery -> {
              videoDb.setDelivery(delivery);
            });

    return videoRepository.save(videoDb);
  }

  /**
   * Returns the video with the given id.
   *
   * @param id The id of the video to return.
   * @return The video with the given id.
   */
  public Video getVideo(Integer id) {
    Optional<Video> optional = videoRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      return null;
    }
  }

  /**
   * Returns all videos.
   *
   * @return The videos.
   */
  public List<Video> getVideos() {
    return videoRepository.findAll();
  }

  /**
   * Deletes the video with the given id.
   *
   * @param id The id of the video to delete.
   */
  public void deleteVideo(Integer id) {
    Optional<Video> optional = videoRepository.findById(id);
    if (optional.isPresent()) {
      videoRepository.delete(optional.get());
    }
  }
}
