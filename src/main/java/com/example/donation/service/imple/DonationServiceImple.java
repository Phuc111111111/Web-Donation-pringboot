package com.example.donation.service.imple;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.donation.DTO.DonationReponsitory;
import com.example.donation.DTO.UserRepository;
import com.example.donation.entity.Donation;
import com.example.donation.entity.UserDonation;
import com.example.donation.service.DonationService;

import jakarta.transaction.Transactional;

@Service
public class DonationServiceImple implements DonationService {
	
	private DonationReponsitory donationRepository;
	
	
	@Autowired
	public DonationServiceImple(DonationReponsitory donationRepository) {
		this.donationRepository = donationRepository;
	}

	@Override
	public List<Donation> findAll() {
		// TODO Auto-generated method stub
		return donationRepository.findAll();
	}

	@Override
	@Transactional
	public Donation saveDonation(Donation donation) {
		// TODO Auto-generated method stub
		return donationRepository.save(donation);
	}

	@Override
	public void deleteById(Integer id) {
		donationRepository.deleteById(id);
		
	}

	@Override
	public Donation findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Donation> donations = donationRepository.findById(id);

		Donation dona = donations.orElse(null);
		
		return dona;
	}

	@Override
	public Page<Donation> findAll(Integer page) {
		PageRequest pageable =  PageRequest.of(page-1, 2);
		return donationRepository.findAll(pageable);
	}
	
	@Override
	public Donation findOne(Integer id) {
		// TODO Auto-generated method stub
		return donationRepository.getOne(id);
	}
	
	public Donation updateMoneytFromStatus(Integer donationid, UserDonation userdonation) {
		// TODO Auto-generated method stub
		// getinformation table donation
		Donation donation = donationRepository.getOne(donationid);
		//get information table UserDonation
		UserDonation userdonation1 = userdonation;
		// Caculater the total amount donated by user
		int moneydonatefromuser = userdonation.getMoney();
		int totalMoneyDonate = donation.getMoney() + moneydonatefromuser;
		donation.setMoney(totalMoneyDonate);
		// save donation from DB
		donationRepository.save(donation);
		
		return donation ;
	}

	
	
	
	
	
}
