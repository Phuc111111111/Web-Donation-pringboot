package com.example.donation.service;

import java.util.List;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.donation.entity.User;
//dùng UserDetailsService để authention
public interface UserService extends UserDetailsService {
	
	User findByemail(String email);
	List<User> findAll();
	User saveUser(User user);
	User findById(Integer id);
	
	void deleteById(Integer id);
	List<User> findbyPhoneNumberContaining(String phoneNumber);
	User findByUserName(String username);
	
}
