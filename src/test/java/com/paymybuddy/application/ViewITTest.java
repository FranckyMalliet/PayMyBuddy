package com.paymybuddy.application;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ViewITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String email = "HectorDupontTest@hotmail.com";

    @Test
    @WithMockUser(username="HectorDupontTest@hotmail.com", password="", roles="USER")
    public void givenAnUrl_ExpectPathToAPageNamedLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ExpectPathToAPageNamedAddConnection() throws Exception {
        mockMvc.perform(get("/connection"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="HectorDupontTest@hotmail.com", password="", roles="USER")
    public void givenAUserEmail_GetAllCorrespondenceOfThisUser() throws Exception {
        mockMvc.perform(get("/correspondences"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="HectorDupontTest@hotmail.com", password="", roles="USER")
    public void givenAUserEmail_GetNameOfAllCorrespondencesOfThisUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="HectorDupontTest@hotmail.com", password="", roles="USER")
    public void givenAnUrl_ExpectPathToAPageNamedHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }
}
