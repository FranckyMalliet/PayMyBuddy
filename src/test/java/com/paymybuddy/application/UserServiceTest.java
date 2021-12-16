package com.paymybuddy.application;

import com.paymybuddy.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService iUserService;

    //Mock User for testing
    private final String firstName = "Hector";
    private final String lastName = "Dupont";
    private final String email = "HectorDupontMock@hotmail.com";
    private final double account = 100.0;
    private final String password = "password2!2!";

}
