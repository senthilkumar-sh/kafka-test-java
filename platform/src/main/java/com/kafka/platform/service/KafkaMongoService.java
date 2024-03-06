package com.kafka.platform.service;

import com.kafka.platform.model.KafkaTestResult;
import com.kafka.platform.repo.KafkaMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KafkaMongoService {

    @Autowired
    private KafkaMongoRepo kafkaMongoRepo;

    public KafkaTestResult saveTestResult(KafkaTestResult kts) {
        KafkaTestResult kafkaTestResult = null;

        try {
            kafkaTestResult = kafkaMongoRepo.save(kts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kafkaTestResult;
    }

    public List<KafkaTestResult> getTestResultById(String testId) {
        return Collections.emptyList();
    }

}
