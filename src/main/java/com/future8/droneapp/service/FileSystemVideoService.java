package com.future8.droneapp.service;

import com.future8.droneapp.config.video.VideoProperties;
import com.future8.droneapp.exception.VideoException;
import com.future8.droneapp.exception.VideoNotFoundException;
import com.future8.droneapp.model.Video;
import com.future8.droneapp.repository.DeliveryRepository;
import com.future8.droneapp.repository.VideoRepository;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemVideoService implements VideoService {

  @Autowired private VideoRepository videoRepository;

  @Autowired private DeliveryRepository deliveryRepository;

  private final Path rootLocation;

  public FileSystemVideoService(VideoProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  @Override
  public void uploadAndSave(Integer id, MultipartFile file) {
    String filename = StringUtils.cleanPath(file.getOriginalFilename());
    filename = filename.replaceAll(" ", "");
    try {
      if (file.isEmpty()) {
        throw new VideoException("Failed to store empty file.");
      }
      Path destinationFile =
          this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        // This is a security check
        throw new VideoException("Cannot store file outside current directory.");
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      }
      Video video =
          new Video(
              filename,
              destinationFile.toUri().toString(),
              "http://localhost:8080/video/files/" + filename);
      deliveryRepository
          .findById(id)
          .ifPresent(
              delivery -> {
                video.setDelivery(delivery);
              });
      videoRepository.save(video);
    } catch (IOException e) {
      throw new VideoException("Failed to store file.", e);
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    } catch (IOException e) {
      throw new VideoException("Failed to read stored files", e);
    }
  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new VideoNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new VideoNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new VideoException("Could not initialize storage", e);
    }
  }

  public List<Video> findAll() {
    return videoRepository.findAll();
  }
}
