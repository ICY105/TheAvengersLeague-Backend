package com.revature;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TheAvengersLeagueApplication implements EnvironmentAware {
	
	public static void main(final String[] args) throws InterruptedException {
		SpringApplication.run(TheAvengersLeagueApplication.class, args);
	}

	@Override
	public void setEnvironment(final Environment environment) {
		Arrays.stream(environment.getActiveProfiles()).forEach(s -> System.out.println(s));
	}

}
