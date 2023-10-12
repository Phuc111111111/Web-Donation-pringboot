package com.example.donation.DTO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.donation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
