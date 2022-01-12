package com.paymybuddy.application;

import com.paymybuddy.application.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import javax.naming.AuthenticationNotSupportedException;

@SpringBootTest
public class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    private final String email = "HectorDupontTest@hotmail.com";

    @Test
    @WithMockUser(username= email, password="", roles="USER")
    public void givenAnAuthenticationSecurityContext_ReturnTheEmailOfAUser(){
        Assertions.assertEquals(securityService.getUserEmail(), email);
    }
}
