package com.kafka.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PlatformConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }

//  @Bean
//  public JaegerTracer jaegerTracer() {
//
//    return new io.jaegertracing.Configuration("servieb")
//        .withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE)
//        .withParam(1))
//        .withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true))
//        .getTracer();
//  }
}
