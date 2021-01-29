package com.microservicios.Oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Oauth2Application implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "1234";
		for (int i = 0; i < 4; i++) {
			String passEncoded = passwordEncoder.encode(password);
			System.out.println(passEncoded);
		}
	}
}
