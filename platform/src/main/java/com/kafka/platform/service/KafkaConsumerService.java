package com.kafka.platform.service;

import com.kafka.platform.common.Constants;
import com.kafka.platform.domain.KafkaMetrics;
import com.kafka.platform.domain.KafkaQueryObject;
import com.kafka.platform.model.KafkaTestResult;
import com.kafka.platform.model.Latency;
import com.kafka.platform.util.Utility;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumerService implements Constants {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaMongoService kafkaMongoService;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private Map metrics = new HashMap(10);

    @Async
    @KafkaListener(topics = "${spring.kafka.topic.name}", autoStartup = "${spring.kafka.consumer.mode}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        //System.out.println("received key = " + consumerRecord.key());
        String key = (consumerRecord.key() != null) ? consumerRecord.key().toString() : "";
        String[] keys = key.split("\\|", -1);
        if (keys.length > 1) {
            String payload = consumerRecord.toString();
            System.out.println("received payload length = " + consumerRecord.value().toString().length());
            //System.out.println("received payload = " + payload);
            processConsumedMessage(keys, consumerRecord);
        }
    }

    private void processConsumedMessage(String[] keys, ConsumerRecord<?, ?> consumerRecord) {
        // Construct Test id with producer name
        String metricsKey = keys[0] + "|" + keys[1];

        // Get the message position fm, fl, m, lm
        String messageKey = keys[2];

        // Consumed payload
        String payload = consumerRecord.toString();

        // Check the message position on given the test id
        if (FIRST_MESSAGE.equals(messageKey)) {
            System.out.println("FIRST_MESSAGE = " + FIRST_MESSAGE);
            KafkaMetrics km = getFirstLastMessageMetrics(keys, consumerRecord);
            metrics.put(metricsKey, km);
        } else if(FIRST_LAST_MESSAGE.equals(messageKey))  {
            System.out.println("FIRST_LAST_MESSAGE = " + FIRST_LAST_MESSAGE);
            KafkaMetrics km = getFirstLastMessageMetrics(keys, consumerRecord);
            // Save to database
        } else if (MESSAGE.equals(messageKey)) {
            System.out.println("MESSAGE = " + MESSAGE);
            if (metrics.containsKey(metricsKey)) {
                // Calculate and Get the newly constructed kafka metrics
                KafkaMetrics km = getMessageMetrics((KafkaMetrics) metrics.get(metricsKey), consumerRecord);
                metrics.put(metricsKey, km);
            }
        } else if (LAST_MESSAGE.equals(messageKey)) {
            System.out.println("LAST_MESSAGE = " + LAST_MESSAGE);
            // Process
            KafkaMetrics km = getMessageMetrics((KafkaMetrics) metrics.get(metricsKey), consumerRecord);
            // Save to database
            saveConsumerMetrics(km);
        }
    }

    private KafkaTestResult saveConsumerMetrics(KafkaMetrics km) {
        KafkaQueryObject kqo = KafkaQueryObject.builder()
                .testId(km.getTestId())
                .totalHits(km.getLatencies().size())
                .messageSizeKB(km.getMessageSizeKB())
                .build();
        KafkaTestResult ktr = kafkaMongoService.saveTestResult(Utility.constructResult(km.getStartTime(), kqo,
                km.getLatencyTime(), km.getMinLatency(), km.getMaxLatency(), km.getLatencies(), km.getName(), CONSUMER));

        return ktr;
    }

    private KafkaMetrics getMessageMetrics(KafkaMetrics km, ConsumerRecord<?, ?> consumerRecord) {
        //int messageReceivedSizeKB = (consumerRecord.value().toString().length() / 1000);
        long startMessageTime = km.getEndTime();
        List<Long> latencies = km.getLatencies();
        long latencyTime = km.getLatencyTime();
        float minLatency = km.getMinLatency();
        float maxLatency = km.getMaxLatency();
        //int messageSizeKB = km.getMessageSizeKB();
        long endMessageTime = System.currentTimeMillis();

        long currentLatency = (endMessageTime - startMessageTime);
        latencies.add(currentLatency);
        latencyTime = latencyTime + currentLatency;

        if (maxLatency < currentLatency) {
            maxLatency = currentLatency;
        }

        if (minLatency > currentLatency) {
            minLatency = currentLatency;
        }

        //km.setMessageSizeKB(messageSizeKB + messageReceivedSizeKB);
        km.setLatencies(latencies);
        km.setLatencyTime(latencyTime);
        km.setMinLatency(minLatency);
        km.setMaxLatency(maxLatency);
        km.setEndTime(endMessageTime);

        return km;
    }

    private KafkaMetrics getFirstLastMessageMetrics(String[] keys, ConsumerRecord<?, ?> consumerRecord) {
        long currentLatency = 0;
        List<Long> latencies = new ArrayList<>(1);
        int messageSizeKB = (consumerRecord.value().toString().length() / 1000);
        latencies.add(currentLatency);
        long start = System.currentTimeMillis();
        System.out.println("start fl " + start);
        return KafkaMetrics.builder()
                .testId(keys[0])
                .name("Consumer1")
                .type(CONSUMER)
                .messageSizeKB(messageSizeKB)
                .startTime(start)
                .endTime(System.currentTimeMillis())
                .latencyTime(0)
                .minLatency(0)
                .maxLatency(0)
                .latencies(latencies)
                .build();
    }

    private KafkaTestResult constructProducerResult(
            long producerStart, KafkaQueryObject kqo,
            long latencyTime, float minLatency, float maxLatency,
            List<Long> latencies, String producerName) {

        long producerEnd = System.currentTimeMillis();
        long producerTimeTaken = (producerEnd - producerStart);
        int timeInSec = 1000;
        int totalHits = kqo.getTotalHits();
        double records = (double) producerTimeTaken / timeInSec;
        double rps = (double) totalHits / records;
        double mb = (double) kqo.getMessageSizeKB() / 1000;
        double mbs = (double) totalHits * mb;
        double tps = (double) producerTimeTaken / 1000;
        double tMBPerSec = mbs / tps;

        Latency latency = Latency.builder()
                .avg(latencyTime / totalHits)
                .min(minLatency)
                .max(maxLatency)
                .p90(Utility.percentile(latencies, 90))
                .p95(Utility.percentile(latencies, 95))
                .p99(Utility.percentile(latencies, 99))
                .build();

        return KafkaTestResult.builder()
                .type(Constants.PRODUCER)
                .testId(kqo.getTestId())
                .name(producerName)
                .timeTaken(producerTimeTaken)
                .recordsPerSec(rps)
                .throughputMBPerSec(tMBPerSec)
                .executionDate(new java.util.Date())
                .latency(latency)
                .build();
    }

    public static void main (String args[]) {
        String key = "faba3fc0-e0b7-4dc5-be86-2b81c4d29900|Producer1|lm";
        String[] keys = key.split("\\|", -1);
        System.out.println("Key length = " + keys.length);
        if (keys.length > 1) {
            for (String k : keys) {
                System.out.println("Key = " + k);
            }
        }
    }
}
