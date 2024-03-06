package com.kafka.platform.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Latency {
    private double avg;
    private double max;
    private double min;
    private double p90;
    private double p95;
    private double p99;

}
