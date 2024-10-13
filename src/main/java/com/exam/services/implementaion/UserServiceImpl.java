package com.exam.services.implementaion;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.services.UserService;
import com.exam.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local=userRepository.findByuserName(user.getUserName());
		if(local!=null) {
			System.out.println("User Already Existed");
			throw new Exception("User Already Existed");
		}else {
			for(UserRole useroles:userRoles) {
				roleRepository.save(useroles.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local=userRepository.save(user);
		}
		
		return local;
	}

	@Override
	public User getUserByUserName(String userName) {
		return userRepository.findByuserName(userName);
	}

	@Override
	public void deleteUser(Long userId){
		 userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(Long userId, User user) {
		User updateUser=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id :"+userId));
		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setEmail(user.getEmail());
		updateUser.setPhone(user.getPhone());
		updateUser.setPassWord(user.getPassWord());
		updateUser.setProfile(user.getProfile());
		updateUser.setUserRoles(user.getUserRoles());
		userRepository.save(updateUser);
		return updateUser;
		
	}
	
	
	
	

}
