package com.kafka.platform.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.concurrent.TimeUnit;

import static java.util.Collections.singletonList;

@Configuration
class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    public String getDatabaseName() {
        return "kafka-test";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {

        builder
                //.credential(MongoCredential.createCredential("", "", "".toCharArray()))
                .applyToClusterSettings(settings  -> {
                    settings.hosts(singletonList(new ServerAddress("127.0.0.1", 27017)));
                });

//        builder.applyToConnectionPoolSettings(settings -> {
//
//            settings.maxConnectionLifeTime(1000000, TimeUnit.MILLISECONDS)
//                    .minSize(100)
//                    .maxSize(10000)
//                    .maintenanceFrequency(10, TimeUnit.MILLISECONDS)
//                    .maintenanceInitialDelay(11, TimeUnit.MILLISECONDS)
//                    .maxConnectionIdleTime(30, TimeUnit.SECONDS)
//                    .maxWaitTime(15, TimeUnit.MILLISECONDS);
//        });
    }
}