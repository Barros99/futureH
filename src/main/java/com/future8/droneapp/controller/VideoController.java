package com.future8.droneapp.controller;

import com.future8.droneapp.dto.VideoDto;
import com.future8.droneapp.exception.VideoNotFoundException;
import com.future8.droneapp.model.Video;
import com.future8.droneapp.service.FileSystemVideoService;
import com.future8.droneapp.service.VideoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

  @Autowired private final VideoService videoService;
  @Autowired private FileSystemVideoService fileSystemVideoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  /** List all videos. */
  @GetMapping
  public List<VideoDto> listVideos() {
    List<Video> videos = fileSystemVideoService.findAll();
    return VideoDto.convert(videos);
  }

  // public ResponseEntity<List<Video>> listUploadedFiles(Model model) throws IOException {

  //   List<Video> videos =
  //       videoService
  //           .loadAll()
  //           .map(
  //               path -> {
  //                 String filename = path.getFileName().toString();
  //                 String url =
  //                     MvcUriComponentsBuilder.fromMethodName(
  //                             VideoController.class, "getFile", path.getFileName().toString())
  //                         .build()
  //                         .toString();
  //                 return new Video(filename, url);
  //               })
  //           .collect(Collectors.toList());
  //   return ResponseEntity.status(HttpStatus.OK).body(videos);
  // }

  /** Serve the file by its name. */
  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {

    Resource file = videoService.loadAsResource(filename);
    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        .body(file);
  }

  /** Upload a video. */
  @PostMapping("/{id}")
  public ResponseEntity<?> handleFileUpload(
      @PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) {
    videoService.uploadAndSave(id, file);
    Map<String, String> response =
        Map.of("message", "Video " + file.getOriginalFilename() + " uploaded successfully!");
    return ResponseEntity.ok().body(response);
  }

  @ExceptionHandler(VideoNotFoundException.class)
  public ResponseEntity<?> handleStorageFileNotFound(VideoNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }
}
