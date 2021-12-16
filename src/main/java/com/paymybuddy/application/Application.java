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

		/*double currentGivenAmount = 36.4;
		String emailTest = "addresseEmailTest@hotmail.com";
		String emailTest2= "addresseEmailTest2@hotmail.com";

		double amount = userService.currentUserAccount("berthildedupont@hotmail.com");
		System.out.println(amount);

		userService.sendMoneyAndUpdateUserAccount(currentGivenAmount, "addresseEmailTest@hotmail.com");
		double amountTest = userService.currentUserAccount("addresseEmailTest@hotmail.com");
		System.err.println(amountTest);

		userService.receiveMoneyAndUpdateUserAccount(currentGivenAmount, "addresseEmailTest2@hotmail.com");

		iUserService.userLogin("addresseEmailTest@hotmail.com", "Test");
		iUserService.userLogin("addresseEmailTest@hotmail.com", "TestABC");

		User user = iUserService.getUserDataFromEmail(emailTest);
		System.out.println(user.getFirstName()
				+ " " + user.getLastName()
				+ " " + user.getEmail()
				+ " " + user.getAmount()
				+ " " + user.getPassword());*/
	}
}
