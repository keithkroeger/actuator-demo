# Demo of Actuator, Micrometer for metrics

## General Areas

1. Created a HelloWorld 'page' available at http://localhost:8080/test/

## Actuator Areas

1. Generated project using https://start.spring.io/

2. Expose all endpoints
    ```yaml
      endpoints:
        web:
          exposure:
            include: "*"
    ```

3. For fun, changed base path to 'anything'
    ```yaml
    management:
     endpoints:
        web:
          base-path: /anything
    
    ```

4. Add info area to application.yml
   
    ```yaml
    info:
      app:
        version: 0.0.1
        name: just anything we wish
        extra: this too
        java:
          version: ${java.version}
      author:
        name: abc
    ```
5. Add Extender for information (which would allow us to note other information desired at runtime)
   
   Class ExtendedInfo does this by retrieving he existing info map and adding to it as well as
   using example of noting a status if desired
   
6. Allow shutdown to test this
   
    ```yaml
    management:
      endpoint:
        shutdown:
          enabled: true
    ```
    By posting to shutdown endpoint, can shut down
    ```text
    curl -X POST http://localhost/anything/shutdown
    ```
    
7.  Show all health
    ```yaml
    management:
      endpoint:
        health:
          show-details: always
    
    ```

8. Add CustomHealthIndicator
    
   Shows just how simple the work here is

9. Adding a failure health through CustomFailingHealthIndicator just to show it

## Micrometer Areas

Micrometer is provided through Spring Boot as well, out of the box.

1. Created a MetricsConfig class

   This configuration allows us to specify any number of tags that we wish.
   This particular example provides tags from application settings as well as fixed values.
   
2. Created custom metric through HelloService

   By creating a custom Bean and then injecting the metrics registry, we can add any values we wish

   We can also see the values at http://localhost:8080/actuator/metrics/received.messages
   
   I also used the @Slf4j annotation within the service simply to show how easy it is to log

3. Created a HealthMetrics metric

   Based on https://micrometer.io/docs/guide/healthAsGauge

4. Created custom LogMeterRegistry that logs metrics from Micrometer into the spring log
   
   By adding a bean to MetricsConfig, we can now run this application
   and see metrics in the consol.

## Links
* https://www.callicoder.com/spring-boot-actuator/
* https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html
