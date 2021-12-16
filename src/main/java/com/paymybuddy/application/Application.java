package com.paymybuddy.application;

import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.ITransferService;
import com.paymybuddy.application.service.IUserService;
import com.paymybuddy.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.transaction.Transactional;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private ICorrespondenceService iCorrespondenceService;

	@Autowired
	private ITransferService iTransferService;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		String password = iUserService.passwordCreator("root");
		System.out.println(password);
	}
}
