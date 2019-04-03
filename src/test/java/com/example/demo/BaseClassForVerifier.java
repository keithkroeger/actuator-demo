package com.example.demo;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@SuppressWarnings("unused")
public abstract class BaseClassForVerifier {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp(){

        RestAssuredMockMvc.mockMvc(mockMvc);
    }
}
