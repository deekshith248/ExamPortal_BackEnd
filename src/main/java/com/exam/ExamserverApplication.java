package com.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.services.UserService;

@SpringBootApplication
public class ExamserverApplication  {

	public static void main(String[] args)  {
		SpringApplication.run(ExamserverApplication.class, args);
	}
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	
//	@Autowired
//	private UserService userService;
	
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/login").allowedOrigins("http://localhost:4200");
//			}
//		};
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("starting execution of code");
//		User user=new User();
//		user.setFirstName("Raj");
//		user.setLastName("Khandelwal");
//		user.setEmail("abc@gmail.com");
//		user.setProfile("default.png");
//		user.setUserName("admin");
//		user.setPassWord(this.bCryptPasswordEncoder.encode("admin"));
//		
//		Role role=new Role();
//		role.setRoleId(11L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet=new HashSet<>();
//		UserRole userRole=new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		
//		this.userService.createUser(user, userRoleSet);	
//	}

}
