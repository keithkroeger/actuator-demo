package com.example.demo.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HelloServiceTest {

    private HelloService testService;

    @Before
    public void setUp() throws Exception {
        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        testService = new HelloService(meterRegistry);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleMessage() {
        assertTrue(testService.getCounter().count()==0);
        testService.handleMessage("test");
        assertTrue(testService.getCounter().count()==1);
    }
}