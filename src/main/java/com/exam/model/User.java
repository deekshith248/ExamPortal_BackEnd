package com.exam.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String profile;
	private boolean enabled = true;
	
	//Lets say you want to delete user so that user roles get automatically deleted so using cascade
	//fetch islie use hua hai taaki jb user fetch ho to uske user roles bhi fetch ho jae
	//agr ye tu mapped by nahi lagyega to ye ek aur table create kr dega jbki hume ye issi table me chahiye 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "user")
	private Set<UserRole> userRoles=new HashSet<>();



	public User() {

	}

	public User(Long id, String userName, String passWord, String firstName, String lastName, String email,
			String phone, String profile, boolean enabled) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.profile = profile;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enablled) {
		this.enabled = enablled;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set=new HashSet<>();
		//Authority to diffrent userRoles like admin and normal user
		this.userRoles.forEach(userroles->{
			set.add(new Authority(userroles.getRole().getRoleName()));
		});
		
		return set;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passWord;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

}
