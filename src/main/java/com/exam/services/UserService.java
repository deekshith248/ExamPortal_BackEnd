package com.exam.services;

import java.util.Set;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	//create
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	public User getUserByUserName(String userName);
	
	public void deleteUser(Long userId);
	
	public 	User updateUser(Long userId,User user);
}
