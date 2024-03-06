package com.kafka.platform.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document("kafka_test_result")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KafkaTestResult {

    @Id
    private String id;

    private String testId;
    private String type;
    private String name;
    private String producerId;
    private int totalHits;
    private long timeTaken;
    private double recordsPerSec;
    private double throughputMBPerSec;
    private Date executionDate;
    private Latency latency;


}
