package com.example.donation.service;


import com.example.donation.entity.UserRole;

public interface UserRoleService {
	UserRole saveUserRole(UserRole user);
	UserRole findByIdUser(Integer id);
}
