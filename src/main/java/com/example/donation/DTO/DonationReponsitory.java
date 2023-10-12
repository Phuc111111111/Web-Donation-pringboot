package com.example.donation.DTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.donation.entity.Donation;

public interface DonationReponsitory extends JpaRepository<Donation, Integer> {

	Page<Donation> findAll(Pageable pageable);
	

}
