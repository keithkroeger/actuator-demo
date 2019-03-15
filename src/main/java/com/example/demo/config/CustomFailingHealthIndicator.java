package com.example.demo.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomFailingHealthIndicator extends AbstractHealthIndicator {

    private Integer counter = 0;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        counter++;

        if ( (counter % 2) == 0 ){
            builder.up()
                    .withDetail("app", "Seems to be down");
            throw new Exception("the counter is even so error");
        } else {
            builder.up()
                    .withDetail("app", "Alive and Kicking ok")
                    .withDetail("error", "Nothing! I'm good.");
        }
    }
}
