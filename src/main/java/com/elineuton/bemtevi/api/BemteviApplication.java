package com.elineuton.bemtevi.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BemteviApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BemteviApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
	}

}
