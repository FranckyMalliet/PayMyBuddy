package com.paymybuddy.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private IUserService iUserService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Mock User for testing
    private final String firstName = "Hector";
    private final String lastName = "Dupont";
    private final String email = "HectorDupontMock@hotmail.com";
    private final double account = 100.0;
    private final String password = "password2!2!";

    //addNewUser
    @Test
    public void givenAUser_AddHimToTheDatabase() throws Exception {
        //GIVEN
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAccount(account);
        user.setPassword(password);

        //WHEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonRequest = objectWriter.writeValueAsString(user);

        //THEN
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }


    @Test
    public void givenAnEmail_ReturnUserInformation() throws Exception {
        mockMvc.perform(get("/user")
                        .param("email", email))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnEmail_DeleteAUserInTheDatabase() throws Exception{
        mockMvc.perform(delete("/user")
                        .param("email", email))
                .andExpect(status().isOk());
    }
}
