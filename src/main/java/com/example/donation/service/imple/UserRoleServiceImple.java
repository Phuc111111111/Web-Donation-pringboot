package com.example.donation.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.donation.DTO.DonationReponsitory;
import com.example.donation.DTO.UserRoleReponsitory;
import com.example.donation.entity.UserRole;
import com.example.donation.service.UserRoleService;

@Service
public class UserRoleServiceImple implements UserRoleService {
	@Autowired
	private UserRoleReponsitory userRoleReponsitory;

	@Override
	public UserRole saveUserRole(UserRole userrole) {
		// TODO Auto-generated method stub
		return userRoleReponsitory.save(userrole);
	}

	@Override
	public UserRole findByIdUser(Integer id) {
		// TODO Auto-generated method stub
		return userRoleReponsitory.findbyIdUser(id);
	}

}
