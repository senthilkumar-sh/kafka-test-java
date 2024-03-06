package com.kafka.orchestrator.controller;

import com.kafka.orchestrator.service.OrchestratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/orchestrator/api")
public class OrchestratorController {
  private static final Logger log =
          LoggerFactory.getLogger(OrchestratorController.class);
  private OrchestratorService orchestratorService;

  public OrchestratorController(OrchestratorService orchestratorService) {
    this.orchestratorService = orchestratorService;
  }

  @GetMapping("/order/{id}")
  public Mono<String> get(@PathVariable("id") Integer id) {

    log.info("serviceA called With id {}", id);
    return orchestratorService.get(id);
  }

  @GetMapping("/execute/test")
  public Flux<String> executeTest() {


//        .subscribe(value -> {
//          log.info("Consumed: " + value);
//        });
//    log.info("serviceA called");
    return Flux.just("red", "white", "blue")
            .log();
  }
}
