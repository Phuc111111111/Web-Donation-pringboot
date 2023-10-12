package com.example.donation.DTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.donation.entity.Role;
import com.example.donation.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
//	@Query(value="SELECT * FROM Users u WHERE u.user_name LIKE ?1",nativeQuery = true)
//	List<User> findByUsername(String username);
	
	@Query(value="SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
	User findByemail(String email);
	
	Optional<User> findById(Integer id);
	void deleteById(Integer id);

	
	List<User> findByPhoneNumberContaining(String phoneNumber);
	
	User findByUserName(String username);
	
	@Query(value="SELECT r.role_name FROM users u "
			+ "INNER JOIN user_role ur ON u.id = ur.user_id "
			+ "INNER JOIN role r ON ur.role_id = r.id "
			+ "WHERE u.id = :id"
			, nativeQuery = true)
	List<String> findRoleNameByIdUser(Integer id);
}
