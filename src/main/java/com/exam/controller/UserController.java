package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*",allowedHeaders="*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		Role role=new Role();
		role.setRoleId(16L);
		role.setRoleName("NORMAL");
		user.setPassWord(this.bCryptPasswordEncoder.encode(user.getPassWord()));
		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		return userService.createUser(user, userRoleSet);
		
	}
	
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String username) {
		return userService.getUserByUserName(username);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable("userId")Long userId,@RequestBody User userDetails) {
		return userService.updateUser(userId, userDetails);
		
	}
	
	

}
