package com.kafka.orchestrator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrchestratorService {

  private WebClient webClient;

  public OrchestratorService(WebClient webClient) {
    this.webClient = webClient;
  }
  public Mono<String> get(Integer id) {

    return webClient.get()
        .uri("http://localhost:9092/platform/api/order/" + id)
        .retrieve()
        .bodyToMono(String.class);
  }
}
