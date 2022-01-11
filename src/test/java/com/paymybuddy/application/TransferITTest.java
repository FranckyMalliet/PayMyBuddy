package com.paymybuddy.application;

import com.paymybuddy.application.service.ITransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
    private static MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Mock User
    private final String email = "HectorDupontTest@hotmail.com";
    private final String password = "password2!2!";

    //Mock Correspondence
    private final String emailCorrespondence = "jeannepavot@hotmail.com";

    // Mock Transfer
    private final double amount = 10;

    @Test
    @WithMockUser(username = email, password = password, roles="USER")
    public void givenATransfer_AddItToTheDatabase_AndUpdateUserAccounts() throws Exception {
        //THEN
        mockMvc.perform(post("/transfer")
                        .param("emailCorrespondence", emailCorrespondence)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = email, password = password, roles="USER")
    public void givenAnAmountOfMoney_SendItToBankAccount() throws Exception {
        //THEN
        mockMvc.perform(put("/sendingMoneyToBank/transfer")
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = email, password = password, roles="ADMIN")
    public void givenAnAmountOfMoney_creditTheUserAccount() throws Exception {
        //THEN
        mockMvc.perform(put("/gettingMoneyFromBank/transfer")
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
    }
}
