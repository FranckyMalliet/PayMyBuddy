package com.paymybuddy.application;

import com.paymybuddy.application.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    private ITransferService iTransferService;


}
