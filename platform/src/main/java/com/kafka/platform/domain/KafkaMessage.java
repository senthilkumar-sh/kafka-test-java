package com.kafka.platform.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KafkaMessage {

    private String id;
    private String message;
    private Long time;
    private Long duration;
    private String ackType;

}