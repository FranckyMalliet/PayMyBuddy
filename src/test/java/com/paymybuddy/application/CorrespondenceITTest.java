package com.paymybuddy.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paymybuddy.application.model.Correspondence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CorrespondenceITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Mock Correspondence
    private final String email = "HectorDupontTest@hotmail.com";
    private final String emailCorrespondence = "jeannepavot@hotmail.com";

    @Test
    @WithMockUser(username= email, password="", roles="USER")
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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .param("email", email)
                        .param("emailCorrespondence", emailCorrespondence))
                .andExpect(status().isOk());
    }
}
