package com.example.demo.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MetricsConfig {

    @Value("${metrics.config.tag1}")
    private String myTag = "default";

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags(
                "tag1", myTag,
                "tag2","fixed");
    }

    @Bean
    LogMeterRegistry logging(){
        Duration dur = Duration.ofSeconds(5);
        LogMeterRegistry logMeterRegistry =  new LogMeterRegistry(dur);
        logMeterRegistry.start();
        return logMeterRegistry;
    }
}
