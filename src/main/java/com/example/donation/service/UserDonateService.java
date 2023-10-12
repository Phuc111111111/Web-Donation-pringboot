package com.example.donation.service;

import java.util.List;


import com.example.donation.entity.UserDonation;


public interface UserDonateService  {
	UserDonation saveUserDonate(UserDonation userdonate);
	List<UserDonation> findAll();
	UserDonation findById(Integer id);
	UserDonation save(UserDonation userDonation);
}
