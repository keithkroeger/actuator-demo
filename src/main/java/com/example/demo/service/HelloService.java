package com.example.demo.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloService {
    public Counter getCounter() {
        return counter;
    }

    private final Counter counter;

    public HelloService(MeterRegistry registry) {
        this.counter = registry.counter("received.messages");
    }

    public void handleMessage(String message) {
        getCounter().increment();
        log.debug("incrementing hello world counter");
    }

}
