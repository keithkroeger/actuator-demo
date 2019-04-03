package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@AutoConfigureJsonTesters
@DirtiesContext
public class OtherVerifier {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
   }

    @Test
    public void should_grant_a_beer_when_person_is_old_enough() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test")
                .content("{\"age\":25}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("Hello, is it me you're looking for?"))
                .andDo(WireMockRestDocs.verify()
                        .jsonPath("$[?(@.age >= 20)]")
                        .contentType(MediaType.valueOf("application/json"))
                        .stub("shouldGrantABeerIfOldEnough"))
                .andDo(MockMvcRestDocumentation.document("shouldGrantABeerIfOldEnough",
                        SpringCloudContractRestDocs.dslContract()));
    }

}
