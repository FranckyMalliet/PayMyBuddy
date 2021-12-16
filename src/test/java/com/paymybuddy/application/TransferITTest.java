package com.paymybuddy.application;

import com.paymybuddy.application.service.ITransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Test
    public void givenAUserEmail_ReturnAllTransferMadeByThisUser() throws Exception {

    }

    @Test
    public void givenATransfer_AddItToTheDatabase() throws Exception {

    }

    @Test
    public void givenAnAmountOfMoney_SendItToBankAccount() throws Exception {

    }

    @Test
    public void givenAnAmountOfMoney_creditTheUserAccount() throws Exception {

    }
}
