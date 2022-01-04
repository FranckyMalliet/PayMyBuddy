package com.paymybuddy.application.listener;

import com.paymybuddy.application.model.Transfer;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.management.remote.JMXAuthenticator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;

import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Component
public class SecuritySpringEventListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private ITransferService iTransferService;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof AuthenticationSuccessEvent) {
            System.out.println("bonjour !");
            List<Transfer> transferList = iTransferService.getAllTransfer();
            for(Transfer transfer : transferList){
                System.out.println(transfer.getDescription());
            }
        }
    }
}
