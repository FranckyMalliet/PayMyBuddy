package com.paymybuddy.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CorrespondenceITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private ICorrespondenceService iCorrespondenceService;

    @Autowired
    private IUserService iUserService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Mock Correspondence for testing
    String email = "HectorDupontTest@hotmail.com";
    String emailCorrespondence = "JeanneDupontTest@hotmail.com";

    @Test
    public void givenACorrespondenceEmail_CreateACorrespondenceInTheDatabase() throws Exception {
        //GIVEN
        Correspondence correspondence = new Correspondence();
        correspondence.setEmailCorrespondence(emailCorrespondence);

        //WHEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonRequest = objectWriter.writeValueAsString(correspondence);

        //THEN
        mockMvc.perform(post("/correspondence")
                        .param("email", email)
                        .param("emailCorrespondence", emailCorrespondence))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUserEmail_GetAllCorrespondenceOfThisUser() throws Exception {
        mockMvc.perform(get("/correspondence")
                .param("email", email)).
                andExpect(status().isOk());
    }
}
