package com.kafka.platform.repo;

import com.kafka.platform.model.KafkaTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface KafkaMongoRepo extends MongoRepository<KafkaTestResult, String> {

    @Query("{name:'?0'}")
    KafkaTestResult findItemById(String id);

    @Query("{testId:'?0'}")
    KafkaTestResult findItemByTestId(String testId);

    public long count();

}
