package com.example.donation.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.donation.entity.Donation;
import com.example.donation.entity.User;
import com.example.donation.entity.UserDonation;


public interface DonationService {
	List<Donation> findAll();
	Donation saveDonation(Donation donation);
	
	void deleteById(Integer id);
	
	Donation findById(Integer id);
	Page<Donation> findAll(Integer page);
	Donation findOne(Integer id);
	Donation updateMoneytFromStatus(Integer donationid, UserDonation userdonation);
}
