package com.kafka.platform.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaTestResponse {
    private String status;
    private String message;
}
