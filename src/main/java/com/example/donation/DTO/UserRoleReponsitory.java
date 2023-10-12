package com.example.donation.DTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.donation.entity.UserRole;

public interface UserRoleReponsitory extends JpaRepository<UserRole, Integer> {
	
	@Query(value = "SELECT * FROM user_role WHERE user_id = :id", nativeQuery = true)
	UserRole findbyIdUser(Integer id);
}
