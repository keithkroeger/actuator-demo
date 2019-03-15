package com.example.demo.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LogMeterRegistry extends StepMeterRegistry {
    @Value("${management.metrics.enabled}")
    private Boolean localCcnfigEnabled;

    /**
     * @param step Governs on what frequency metrics are logged
     */
    public LogMeterRegistry(Duration step) {
        super(new StepRegistryConfig() {
            @Override
            public String prefix() {
                return "log";
            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public Duration step() {
                return step;
            }
        }, Clock.SYSTEM);
    }

    @Override
    protected void publish() {
        if (localCcnfigEnabled){
            for (Meter meter : getMeters()) {
                log.info(meter.getId().toString());
                for (Measurement measurement : meter.measure()) {
                    log.info(measurement.getStatistic().toString() + "=" + measurement.getValue());
                }
            }
        }
    }

    @Override
    protected TimeUnit getBaseTimeUnit() {
        return TimeUnit.SECONDS;
    }
}
