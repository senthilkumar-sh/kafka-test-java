package com.kafka.platform.domain;

import com.kafka.platform.model.Latency;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KafkaMetrics {

    private String testId;
    private String type;
    private String name;
    private int totalHits;
    private long startTime;
    private long endTime;
    private List<Long> latencies;
    private long latencyTime;
    private float maxLatency;
    private float minLatency;
    private int messageSizeKB;

}
