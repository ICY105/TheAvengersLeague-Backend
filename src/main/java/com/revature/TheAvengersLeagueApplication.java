package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.models.GameHandler;

@SpringBootApplication
public class TheAvengersLeagueApplication {
	
	public static void main(final String[] args) throws InterruptedException {
		SpringApplication.run(TheAvengersLeagueApplication.class, args);
		
		final Thread thread = new Thread(GameHandler.getInstance());
		thread.start();
	}

}
