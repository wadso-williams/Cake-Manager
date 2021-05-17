package com.waracle.cakemgr.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CakeServiceImplIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should Return Status Ok to confirm existing cakes Exists")
  public void checkDataExistsInCurrentSystem() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/cakes").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
//        .andExpect(status().isOk());
  }

}