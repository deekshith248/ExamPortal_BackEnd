package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtils;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.services.implementaion.UserDetailsServiceImpl;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class AuthenticationController {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//generate Token
	@PostMapping("/generate-token")
	@CrossOrigin("*")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUserName(),jwtRequest.getPassWord());
		}catch(UsernameNotFoundException e) {
			throw new Exception("User Not Found");
		}
		UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		String token=this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}catch(DisabledException e) {
			throw new Exception("User Disabled");
		}catch(BadCredentialsException e) {
			throw new Exception("Bad Credential");
		}
		
	}
	//returns the detail of current user
	@GetMapping("/current-user")
	private User getCurrentUser(Principal principal) {
		return (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
	}
}
