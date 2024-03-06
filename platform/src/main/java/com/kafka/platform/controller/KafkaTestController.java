package com.kafka.platform.controller;

import com.kafka.platform.domain.KafkaQueryObject;
import com.kafka.platform.model.KafkaTestResponse;
import com.kafka.platform.service.KafkaProducerService;
import com.kafka.platform.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/execute/producer")
public class KafkaTestController {

  private static final Logger log =
          LoggerFactory.getLogger(KafkaTestController.class);

  private PlatformService platformService;

  @Autowired
  private KafkaProducerService kafkaProducerService;

  @Value("${spring.kafka.consumer.mode}")
  private boolean consumerMode;

  public KafkaTestController(PlatformService platformService) {
    this.platformService = platformService;
  }

  @GetMapping("/test")
  public ResponseEntity<KafkaTestResponse> executeTest(@RequestParam(value="totalHits", defaultValue="10") int totalHits,
                                            @RequestParam(value="producers", defaultValue="1") int producers,
                                            @RequestParam(value="partitions", defaultValue="1") int partitions,
                                            @RequestParam(value="topic", defaultValue="koffshop-events") String topic,
                                            @RequestParam(value="consumers", defaultValue="1") int consumers,
                                            @RequestParam(value="messageSizeKB", defaultValue="10") int messageSizeKB,
                                            @RequestParam(value="testId", required = false) String testId) {

        String status = "INITIATED";
        String message = "Producing messages";

        if (!consumerMode) {
            List<CompletableFuture<String>> futures = new ArrayList<>();
            for(int i = 1; i <= producers; i++) {
                String producerName = "Producer" + i;
                String testExecutionId = StringUtils.hasText(testId) ? testId : UUID.randomUUID().toString();
                futures.add(CompletableFuture.supplyAsync(() -> {
                    kafkaProducerService.testExecution(
                            KafkaQueryObject.builder()
                                    .totalHits(totalHits)
                                    .producers(producers)
                                    .consumers(consumers)
                                    .topic(topic)
                                    .messageSizeKB(messageSizeKB)
                                    .testId(testExecutionId)
                                    .partitions(partitions)
                                    .build(), producerName
                    );
                    return "true";
                }));
            }

            CompletableFuture<List<String>> aggregate = CompletableFuture.completedFuture(new ArrayList<>());
            for (CompletableFuture<String> future : futures) {
                aggregate = aggregate.thenCompose(list -> {
                    try {
                        list.add(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return CompletableFuture.completedFuture(list);
                });
            }
            final List<String> results = aggregate.join();
            for (int i = 0; i < producers; i++) {
                System.out.println("Finished " + results.get(i));
            }

        } else {
            status = "CONSUMER_MODE";
            message = "Running as consumer mode cannot produce messages";
        }
        return new ResponseEntity<KafkaTestResponse>(KafkaTestResponse.builder().status(status).message(message).build() , HttpStatus.OK);
    }

}
