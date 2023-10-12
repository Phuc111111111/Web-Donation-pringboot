package com.example.donation.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.donation.DTO.RoleRepository;
import com.example.donation.DTO.UserRepository;
import com.example.donation.DTO.UserRoleReponsitory;
import com.example.donation.entity.Role;
import com.example.donation.entity.User;
import com.example.donation.entity.UserRole;
import com.example.donation.service.UserService;
@Service
public class UserServiceImple implements UserService {
	@Autowired
	private UserRepository useRepository;
	@Autowired
	private UserRoleReponsitory userRoleReponsitory;
	@Autowired
	private RoleRepository roleRepository;
	

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return useRepository.findAll();
	}

	@Override
	public User findByemail(String email) {
		// TODO Auto-generated method stub
		return useRepository.findByemail(email);
	}
	
	

	@Override
	@Transactional
	public User saveUser(User user) {
		return useRepository.save(user);
	}

	@Override
	public User findById(Integer id) {
		Optional<User> user = useRepository.findById(id);
		User user1 = user.orElse(null);// nếu user có giá trị trả về giá trị nếu không trả về giá trị cung cấp 
		return user1;
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteById(Integer id) {
		UserRole userRole = userRoleReponsitory.findbyIdUser(id);
		if(userRole!=null) {
			userRoleReponsitory.delete(userRole);
		}
		useRepository.deleteById(id);	
	}
	@Override
	public List<User> findbyPhoneNumberContaining(String phoneNumber) {
		return useRepository.findByPhoneNumberContaining(phoneNumber);	
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return useRepository.findByUserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = useRepository.findByemail(email);//1 lấy user theo email nguoi dung
		  if (user == null) {
			  System.out.println("User not found! " + email);
	            throw new UsernameNotFoundException("Email " + email + " was not found in the database");
	        }
		List<String> listRoles = useRepository.findRoleNameByIdUser(user.getId());//2 lay danh sách các roleName theo id của người dùng
		if(listRoles.size()==0) {
			System.out.println("khong tim thay role");
		}
		
		List<GrantedAuthority> grantLists = new ArrayList<GrantedAuthority>();//thực hiện thêm các role vào grantLists
		if (listRoles != null) {
            for (String role : listRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantLists.add(authority);
            }
        }
		// User details sẽ cần 3 tham số để authen (email, password,list<GrantedAuthority>) để cung cấp cho spring security
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), grantLists);
		return userDetails;
	}
	
	
}
