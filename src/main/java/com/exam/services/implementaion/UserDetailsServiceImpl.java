package com.exam.services.implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.config.JwtUtils;
import com.exam.model.User;
import com.exam.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtils jwtutils;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByuserName(username);
		if (user == null) {
//			username=this.jwtutils.extractUsername(username);
//			user= this.userRepository.findByuserName(username);
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("Invalid Credentials !!");
		}
		return user;
	}

}
