package com.paymybuddy.application;


import com.paymybuddy.application.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @Test
    public void givenAnAmount_CalculateFee() throws Exception {
        //GIVEN
        double amount = 25;

        //WHEN
        double fee = transferService.collectFee(amount);

        //THEN
        Assertions.assertEquals(0.125, fee);
    }
}
