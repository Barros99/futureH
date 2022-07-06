package com.future8.droneapp;

import com.future8.droneapp.config.video.VideoProperties;
import com.future8.droneapp.service.VideoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties(VideoProperties.class)
@EnableSwagger2
public class DroneDeliveryApplication {

  public static void main(String[] args) {
    SpringApplication.run(DroneDeliveryApplication.class, args);
  }

  @Bean
  CommandLineRunner init(VideoService videoService) {
    return (args) -> {
      videoService.deleteAll();
      videoService.init();
    };
  }
}
