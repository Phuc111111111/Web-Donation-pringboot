package com.example.donation.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.donation.DTO.UserDonateRepository;
import com.example.donation.entity.UserDonation;
import com.example.donation.service.UserDonateService;

@Service
public class UserDonateServiceImple implements UserDonateService {
	
	@Autowired
	UserDonateRepository userDonateRepository;

	@Override
	public UserDonation saveUserDonate(UserDonation userdonate) {
		return userDonateRepository.save(userdonate);
	}

	@Override
	public List<UserDonation> findAll() {
		// TODO Auto-generated method stub
		return userDonateRepository.findAll();
	}

	@Override
	public UserDonation findById(Integer id) {
		// TODO Auto-generated method stub
		return userDonateRepository.findbyId(id);
	}

	@Override
	public UserDonation save(UserDonation userDonation) {
		// TODO Auto-generated method stub
		return userDonateRepository.save(userDonation);
	}
	
}
