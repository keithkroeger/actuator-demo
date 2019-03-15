package com.example.demo.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HealthMetrics {
    private CompositeHealthIndicator healthIndicator;

    private String serviceName = "testing";
    public HealthMetrics(HealthAggregator healthAggregator, List<HealthIndicator> healthIndicatorList, MeterRegistry meterRegistry){
        healthIndicator = new CompositeHealthIndicator(healthAggregator);

        for (Integer i=0;i<healthIndicatorList.size();i++){
            healthIndicator.addHealthIndicator(i.toString(),healthIndicatorList.get(i));
        }

        String metricName=serviceName+".health";

        meterRegistry.gauge(metricName,Tags.of("status","up"),healthIndicator,
                health -> Status.UP.equals(health.health().getStatus()) ? 1 : 0);
        meterRegistry.gauge(metricName,Tags.of("status","down"),healthIndicator,
                health -> Status.DOWN.equals(health.health().getStatus()) ? 1 : 0);

    }
}
