package com.example.donation.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.donation.entity.Donation;
import com.example.donation.entity.Role;
import com.example.donation.entity.User;
import com.example.donation.entity.UserDonation;
import com.example.donation.entity.UserRole;
import com.example.donation.service.DonationService;
import com.example.donation.service.UserDonateService;
import com.example.donation.service.UserRoleService;
import com.example.donation.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	@Autowired
	DonationService donationService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	UserDonateService userDonateService;


	

	
	@GetMapping("/home")
    public String home() {
	
        return "admin/view/home" ;
    }
	
	@GetMapping("/account")//listaccount
    public String account(@RequestParam(name = "query", required = false) String querysearch,Model model,Principal principal ) {
		
		//User user = userService.findByemail(principal.getName());
		
		// get users from service
        List<User> users;
        
        if (querysearch != null && !querysearch.isEmpty()) {
        	users = userService.findbyPhoneNumberContaining(querysearch);

        } else {
        	users = userService.findAll();
        	
        }
        
        model.addAttribute("users", users);
        
        return "admin/view/account";
    }
	
	@PostMapping("/addUser")
	public String addUser(RedirectAttributes model,
							@RequestParam("fullName") String fullname,
							@RequestParam("email") String email,
							@RequestParam("phoneNumber") String phoneNumber,
							 @RequestParam("address") String address,
	                          @RequestParam("userName") String userName,
	                          @RequestParam("password") String password,
	                          @RequestParam("roleId") Integer roleId) {

       
		User user= new User();
		
		user.setFullName(fullname);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setAddress(address);
		user.setUserName(userName);
		user.setPassword(password);
		user.setStatus(1);//hoạt động
		
		//luu user moi vao database
	    userService.saveUser(user);
	    
	    
	    //thiet lap role
	    Role role = new Role();
		role.setId(roleId);//id=1,2
		//lay thong tin nguoi dung vua them vao
	    User user1 = userService.findByUserName(userName);
	    //thiet lap userRole , tao moi quan he giua 2 bang user va role
	    UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user1);
		//luu userRole vao database
		userRoleService.saveUserRole(userRole);
	    

		return "redirect:/admin/account";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(RedirectAttributes model,
			@RequestParam("id") Integer id,
			@RequestParam("fullName") String fullname,
			@RequestParam("email") String email,
			@RequestParam("phoneNumber") String phoneNumber,
			 @RequestParam("address") String address,
              @RequestParam("userName") String userName,
              @RequestParam("password") String password,
              @RequestParam("roleId") Integer roleId, User user){
		
		
		User user1 = userService.findById(id);
		String message;
		if (user1 != null) {// nếu tồn tại thì update user
			user.setId(id);
			user.setFullName(fullname);
			user.setEmail(email);
			user.setPhoneNumber(phoneNumber);
			user.setAddress(address);
			user.setUserName(userName);
			user.setPassword(password);
			user.setStatus(1);// hoạt động
			userService.saveUser(user);

			Role role = new Role();
			role.setId(roleId);// id=1,2

			// thay doi bang userRole , tao moi quan he giua 2 bang user va role
			UserRole userRole = userRoleService.findByIdUser(id);
			userRole.setRole(role);
			userRole.setUser(user1);
			userRoleService.saveUserRole(userRole);
		}else {
            throw new EntityNotFoundException("Không tìm thấy User với id " + id);
            }
		
		return "redirect:/admin/account";
	}
	
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") Integer id ) {
		userService.deleteById(id);
		return "redirect:/admin/account";
	}
	
	
	// chức năng khóa mở khóa trạng thái hoạt động
	 @GetMapping("/lockUser")
	    public String lockUser(@RequestParam("userId") Integer Id) {
		 
		 System.out.println("cfd");
	        // get user by id
	        User user = userService.findById(Id);
	        if(user==null) {
	        	 throw new RuntimeException("Không tìm thấy người dùng với userId " + Id);
	        }
	        // set status = 2 : Khóa
	        user.setStatus(1);
	        // update user in database
	        userService.saveUser(user);
	        
	        return "redirect:/admin/account";
	    }

	    @GetMapping("/unlockUser")
	    public String unlockUser(@RequestParam("userId") Integer Id) {
	    	
	    	System.out.println("abc");
	        // get user by id
	        User user = userService.findById(Id);
	        if(user==null) {
	        	System.out.println("lõi");
	        }

	        // set status = 0 : Hoạt động
	        user.setStatus(0);

	        // update user in database
	        userService.saveUser(user);

	        return "redirect:/admin/account";
	    }
	    
	    /////////////// quan ly donation-----------------------------------------------------------
	    @GetMapping("/donation")
	    public String donation(Model model, @RequestParam(name ="page",defaultValue = "1") Integer page) {
	        // get users from service
//	    	 Page<Donation> pages = donationService.findAll(page);
//			 List<Donation> donations = pages.getContent();
//	    	
	        List<Donation> donations = donationService.findAll();

	        // add users to the Model
	        model.addAttribute("donations", donations);

	        return "admin/view/donation";
	    }
	    
	    @PostMapping("/addDonation")
	    public String addDonation(RedirectAttributes theModel,
	                              @RequestParam("code") String code,
	                              @RequestParam("name") String name,
	                              @RequestParam("startDate") String startDate,
	                              @RequestParam("endDate") String endDate,
	                              @RequestParam("organizationName") String organizationName,
	                              @RequestParam("phoneNumber") String phoneNumber,
	                              @RequestParam("description") String description) {

	        // money = 0 when create donation
	        int money = 0;

	        // status = 1 : Mới tạo
	        int status = 1;
	        
	        // create donation and save to database
	        Donation donation = new Donation(code, name, startDate, endDate, organizationName, phoneNumber, money, status, description);
	        
	        donationService.saveDonation(donation);

	        // set attribute addDonationSuccess
	        theModel.addFlashAttribute("addDonationSuccess", "Successfully");

	        return "redirect:/admin/donation";
	    }
	    
	    @PostMapping("/deleteDonation")
	    public String deleteDonation(@RequestParam("donationId") int Id) {
	        donationService.deleteById(Id);
	        return "redirect:/admin/donation";
	    }

	    @PostMapping("/updateDonation")
	    public String updateDonation(@RequestParam("donationId") Integer Id,
	                                 @RequestParam("code") String code,
	                                 @RequestParam("name") String name,
	                                 @RequestParam("startDate") String startDate,
	                                 @RequestParam("endDate") String endDate,
	                                 @RequestParam("organizationName") String organizationName,
	                                 @RequestParam("phoneNumber") String phoneNumber,
	                                 @RequestParam("money") int money,
	                                 @RequestParam("status") int status,
	                                 @RequestParam("description") String description,Donation donations) {
	    	//tim kiếm bởi id xem người dùng tồn tại không
	        Donation donation = donationService.findById(Id);
	        if(donation!=null) {
	        	donations = new Donation(code, name, startDate, endDate, organizationName, phoneNumber, money, status, description);
	        	donations.setId(donation.getId());// thiết lập id 
	        }

	        // update donation in database
	        donationService.saveDonation(donations);

	        return "redirect:/admin/donation";
	       
	    }
	    
	    @GetMapping("/detailDonation")
	    public String detailDonation(@RequestParam("donationId") Integer Id,Model model) {
	    	//tim kiếm bởi id xem người dùng tồn tại không
	        Donation donation = donationService.findById(Id);
	        if(donation!=null) {
	        	model.addAttribute("donation",donation);
	        }

	        return "redirect:/admin/donation";
	       
	    }
	    
	    @PostMapping("/statusToDonate")
	    public String statusDonation(@RequestParam("donationId") int donationId) {
	        // get donation by id
	        Donation donation = donationService.findById(donationId);
	        if(donation!=null) {
	        	if(donation.getStatus()==1) {//status ==1: moi khoi tao
	        		donation.setStatus(2);//status ==2 : dang quyen gop
	        	}else if(donation.getStatus()==2) {
	        		donation.setStatus(3); //status ==3 : ket thuc quyen gop
	        	}else if(donation.getStatus()==3) {
	        		donation.setStatus(4);//status ==4 : Đóng Quyên Góp 
	        	}
	        }

	        // update donation in database
	        donationService.saveDonation(donation);

	        return "redirect:/admin/donation";
	        
	    }
	    
	    // ------------------- quan lý ủng hộ tiền---------------------
	    
	    @GetMapping("/listUserDonate")//listaccount
	    public String listUserDonate(@RequestParam(name = "query", required = false) String querysearch,Model model,Principal principal ) {
		
			//User user = userService.findByemail(principal.getName());
	        List<UserDonation> userdonations= userDonateService.findAll();

	        model.addAttribute("userdonations", userdonations);
	        
	        return "admin/view/userDonation";
	    }
	    
	    @PostMapping("/statusToUserdonate")
	    public String statusToUserdonate(@RequestParam("userdonationId") Integer userdonationId) {
	    	//tim kiếm bởi id xem người dùng tồn tại không
	        UserDonation userdonation = userDonateService.findById(userdonationId);
	        if(userdonation!=null) {
	        	userdonation.setStatus(1);
	        	donationService.updateMoneytFromStatus(userdonationId, userdonation);
	        }
	        
	        
	        userDonateService.save(userdonation);
	        
	        return "redirect:/admin/listUserDonate";
	       
	    }
}
