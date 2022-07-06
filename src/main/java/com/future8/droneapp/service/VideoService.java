package com.future8.droneapp.service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void init();

  void uploadAndSave(Integer id, MultipartFile file);

  Stream<Path> loadAll();

  Path load(String filename);

  Resource loadAsResource(String filename);

  void deleteAll();
}
