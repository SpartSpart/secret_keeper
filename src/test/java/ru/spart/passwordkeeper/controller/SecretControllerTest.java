package ru.spart.passwordkeeper.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecretControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void givenSecretWhenPostShouldOk() throws Exception {
        mockMvc.perform(post("/api/secrets")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"description\":\"test_description\",\"login\":\"test_login\",\"password\":\"test_password\",\"user_id\":\"test_user_id\"}")
        ).andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void givenSecretWhenGetShouldOk() throws Exception {
        mockMvc.perform(get("/api/secret/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
        .andExpect(content().json("{}"));
    }

    @Test
    @Ignore
    public void givenSecretWhenPutShouldOk() throws Exception {
        mockMvc.perform(put("/api/secret/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"description\":\"test_description\",\"login\":\"test_login\",\"password\":\"test_password\"}")
        ).andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void givenSecretWhenDeleteShouldOk() throws Exception {
        mockMvc.perform(delete("/api/secret/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

}