package com.kafka.platform.service;

import com.kafka.platform.common.Constants;
import com.kafka.platform.domain.KafkaMessage;
import com.kafka.platform.domain.KafkaQueryObject;
import com.kafka.platform.model.KafkaTestResult;
import com.kafka.platform.model.Latency;
import com.kafka.platform.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService implements Constants {

    @Autowired
    private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @Autowired
    private KafkaMongoService kafkaMongoService;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Async
    public CompletableFuture<Boolean> testExecution(KafkaQueryObject kqo, String producerName) {
        long latencyTime = 0;
        float maxLatency = 0;
        float minLatency = 0;
        int totalHits = kqo.getTotalHits();
        int messageSizeKB = kqo.getMessageSizeKB();
        List<Long> latencies = new ArrayList<>(totalHits);
        long producerStart = System.currentTimeMillis();
        String message = Utility.generateRandomString(kqo.getMessageSizeKB() * 1000);
        kqo.setProducerId(UUID.randomUUID().toString());
        for (int i = 1; i <= totalHits; i++) {
            long startMessageTime = System.currentTimeMillis();
            String key = getKey(kqo, producerName, i);

            kafkaTemplate.send(topicName, key,
                    KafkaMessage.builder()
                            .id(UUID.randomUUID().toString())
                            .message(message)
                            .build()
                    );

            long timeTakenMessageMs = System.currentTimeMillis() - startMessageTime;
            long endMessageTime = System.currentTimeMillis();
            long currentLatency = (endMessageTime - startMessageTime);
            latencies.add(currentLatency);
            latencyTime = latencyTime + currentLatency;

            if (maxLatency < currentLatency) {
                maxLatency = currentLatency;
            }

            if (i == 1) {
                minLatency = currentLatency;
            } else if (minLatency > currentLatency) {
                minLatency = currentLatency;
            }
        }

        kafkaMongoService.saveTestResult(Utility.constructResult(producerStart, kqo,
                latencyTime, minLatency, maxLatency, latencies, producerName, PRODUCER));

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    private String getKey(KafkaQueryObject kqo, String producerName, int i) {
        String key = kqo.getTestId() + "|" + producerName;
        if (i == 1) {
            if (i == kqo.getTotalHits()) {
                key = key + KEY_SEPERATOR + FIRST_LAST_MESSAGE + KEY_SEPERATOR + kqo.getProducerId();
            } else {
                key = key + KEY_SEPERATOR + FIRST_MESSAGE + KEY_SEPERATOR + kqo.getProducerId();
            }
        } else if (i == kqo.getTotalHits()) {
            key = key + KEY_SEPERATOR + LAST_MESSAGE + KEY_SEPERATOR + kqo.getProducerId();
        } else {
            key = key + KEY_SEPERATOR + MESSAGE + KEY_SEPERATOR + kqo.getProducerId();
        }

        return key;
    }

}
