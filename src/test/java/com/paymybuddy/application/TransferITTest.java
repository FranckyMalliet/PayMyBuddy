package com.paymybuddy.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.model.Transfer;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.ITransferService;
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
public class TransferITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ITransferService transferService;

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

    //Mock Correspondence for testing
    String emailCorrespondence = "JeanneDupontTest@hotmail.com";

    // Mock Transfer for testing
    double amount = 10;
    String description = "This is a transfer test";

    @Test
    public void givenAUserEmail_ReturnAllTransferMadeByThisUser() throws Exception {
        mockMvc.perform(get("/transfer")
                .param("email"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenATransfer_AddItToTheDatabase() throws Exception {
        //GIVEN
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAccount(account);
        user.setPassword(password);

        Correspondence correspondence = new Correspondence();
        correspondence.setUser(user);
        correspondence.setEmailCorrespondence(emailCorrespondence);

        Transfer transfer = new Transfer();
        transfer.setCorrespondence(correspondence);
        transfer.setAmount(amount);
        transfer.setDescription(description);

        //WHEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonRequest = objectWriter.writeValueAsString(transfer);

        //THEN
        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnAmountOfMoney_SendItToBankAccount() throws Exception {
        //GIVEN
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAccount(account);
        user.setPassword(password);

        Correspondence correspondence = new Correspondence();
        correspondence.setUser(user);
        correspondence.setEmailCorrespondence(emailCorrespondence);

        Transfer transfer = new Transfer();
        transfer.setCorrespondence(correspondence);
        transfer.setAmount(amount);
        transfer.setDescription(description);

        //WHEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonRequest = objectWriter.writeValueAsString(transfer);

        //THEN
        mockMvc.perform(put("/sendingMoneyToBank/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnAmountOfMoney_creditTheUserAccount() throws Exception {
        //GIVEN
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAccount(account);
        user.setPassword(password);

        Correspondence correspondence = new Correspondence();
        correspondence.setUser(user);
        correspondence.setEmailCorrespondence(emailCorrespondence);

        Transfer transfer = new Transfer();
        transfer.setCorrespondence(correspondence);
        transfer.setAmount(amount);
        transfer.setDescription(description);

        //WHEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonRequest = objectWriter.writeValueAsString(transfer);

        //THEN
        mockMvc.perform(get("/gettingMoneyFromBank/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }
}
