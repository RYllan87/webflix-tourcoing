package fr.formation.webflix;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.enums.Gender;
import fr.formation.webflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WebflixApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(WebflixApplication.class, args);
	}

//	@Bean
//	public void saveUser(){
//		UserEntity user = new UserEntity();
//		user.setEmail("samuel.michaux@ik.me");
//		user.setPassword("samsamsam");
//		user.setFirstname("samuel");
//		user.setLastname("Michaux");
//		user.setCountry("France");
//		user.setDateCreated(Calendar.getInstance());
//		user.setGender(Gender.MR);
//
////		System.out.println(user);
////		user = userService.save(user);
////		System.out.println(user);
//
//	}

	@Bean
	public void deleteAllUser(){
		userService.deleteAll();
	}
}

