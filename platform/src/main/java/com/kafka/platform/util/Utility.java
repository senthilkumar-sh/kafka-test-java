package com.kafka.platform.util;

import com.kafka.platform.common.Constants;
import com.kafka.platform.domain.KafkaQueryObject;
import com.kafka.platform.model.KafkaTestResult;
import com.kafka.platform.model.Latency;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utility {

    public static long percentile(List<Long> values, double percentile) {
        Collections.sort(values);
        int index = (int) Math.ceil((percentile / 100) * values.size());
        return values.get(index - 1);
    }

    public static String generateRandomString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static KafkaTestResult constructResult(
            long start, KafkaQueryObject kqo,
            long latencyTime, float minLatency, float maxLatency,
            List<Long> latencies, String producerName, String type) {

        long end = System.currentTimeMillis();
        long timeTaken = (end - start);
        int timeInSec = 1000;
        int totalHits = kqo.getTotalHits();
        double records = (double) timeTaken / timeInSec;
        double rps = (double) totalHits / records;
        double mb = (double) kqo.getMessageSizeKB() / 1000;
        double mbs = (double) totalHits * mb;
        double tps = (double) timeTaken / 1000;
        double tMBPerSec = mbs / tps;

        System.out.println("start " + start);
        System.out.println("timeTaken " + timeTaken);
        System.out.println("type " + type);
        //System.out.println("totalHits " + totalHits);
        //System.out.println("Size KB " + kqo.getMessageSizeKB());
        Latency latency = Latency.builder()
                .avg(latencyTime / totalHits)
                .min(minLatency)
                .max(maxLatency)
                .p90(Utility.percentile(latencies, 90))
                .p95(Utility.percentile(latencies, 95))
                .p99(Utility.percentile(latencies, 99))
                .build();

        return KafkaTestResult.builder()
                .type(type)
                .totalHits(kqo.getTotalHits())
                .producerId(kqo.getProducerId())
                .testId(kqo.getTestId())
                .name(producerName)
                .timeTaken(timeTaken)
                .recordsPerSec(rps)
                .throughputMBPerSec(tMBPerSec)
                .executionDate(new java.util.Date())
                .latency(latency)
                .build();
    }
}
