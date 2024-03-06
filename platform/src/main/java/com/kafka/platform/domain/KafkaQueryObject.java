package com.kafka.platform.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KafkaQueryObject {
    private int totalHits;
    private int messageSizeKB;
    private int producers;
    private int consumers;
    private String topic;
    private String testId;
    private int partitions;
}
