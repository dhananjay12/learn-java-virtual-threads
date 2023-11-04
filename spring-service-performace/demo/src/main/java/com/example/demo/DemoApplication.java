package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@RestController
@Slf4j
class LoadTestController {

	Faker faker = new Faker();

	@GetMapping(value={"/address", "/address/{sleep}"})
	public Address address(@PathVariable Optional<Long> sleep) throws InterruptedException {
		log.info("hey, I'm doing something at :: " + new Timestamp(System.currentTimeMillis()));
		if (sleep.isEmpty()) {
			sleep = Optional.of(200L);
		}
		Thread.sleep(sleep.get());
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String streetAddress = faker.address().streetAddress();
		//Return Address object using above values
		return new Address(streetAddress, name, firstName, lastName);
	}
}

record Address(String streetAddress, String city, String state, String zipCode) {
}