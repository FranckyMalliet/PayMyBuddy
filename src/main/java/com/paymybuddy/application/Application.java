package com.paymybuddy.application;
import com.paymybuddy.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
