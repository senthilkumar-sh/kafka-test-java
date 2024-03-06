package com.kafka.platform.controller;

import com.kafka.platform.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@RestController
@RequestMapping("/platform/api")
public class PlatformController {

  private static final Logger log =
          LoggerFactory.getLogger(PlatformController.class);

  private PlatformService platformService;

  public PlatformController(PlatformService platformService) {
    this.platformService = platformService;
  }

  @GetMapping("/order/{id}")
  public Mono<String> get(@PathVariable("id") Integer id) {
    log.info("serviceb called With id {}", id);
    return platformService.get(id);
  }

  @GetMapping("/execute/test")
  public Flux<String> executeTest() {
    return Flux.just("red", "white", "blue", "orange", "green", "yellow")
            .delayElements(Duration.ofSeconds(1))
            .log();
  }
}
