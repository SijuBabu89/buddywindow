package com.buddywindow.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.impl.TextCodec;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
		System.out.println(TextCodec.BASE64.decode("BuddyWIndow"));
		System.out.println("Buddywindow : Everything is working fine ......!");
	}

}
