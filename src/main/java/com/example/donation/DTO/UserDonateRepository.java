package com.example.donation.DTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.donation.entity.UserDonation;


public interface UserDonateRepository extends JpaRepository<UserDonation, Integer> {
	List<UserDonation> findAll();
	@Query(value = "SELECT * FROM user_donation WHERE id = :id", nativeQuery = true)
	UserDonation findbyId(Integer id);
}
