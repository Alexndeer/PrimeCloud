package ru.shop.PrimeCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PrimeCloudApplication {

	public static void main(String[] args) {
		System.out.println("sanya 4e4en");
		ConfigurableApplicationContext ctx = SpringApplication.run(PrimeCloudApplication.class, args);
	}
}
