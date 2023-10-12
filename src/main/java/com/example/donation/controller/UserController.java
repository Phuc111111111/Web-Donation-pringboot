package com.example.donation.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.donation.entity.Donation;
import com.example.donation.entity.User;
import com.example.donation.entity.UserDonation;
import com.example.donation.service.DonationService;
import com.example.donation.service.UserDonateService;
import com.example.donation.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	DonationService donationService;
	@Autowired
	UserService userService;
	@Autowired
	UserDonateService userDonateService;
	
	  @PostMapping("/UserDonate")
	    public String UserDonate(RedirectAttributes theModel, Principal principal,
                @RequestParam("name") String name,
                @RequestParam("money") int money,
                @RequestParam("message") String message,
                @RequestParam("donationId") int donationId) throws Exception {
		  	
		  
		  	int status = 0;
	        String date = java.time.LocalDate.now().toString();
	        
	        String email = principal.getName();
	        if(email==null) {
	        	throw new Exception("No have information user");
	        }
	        // get information donation
	        Donation donation = donationService.findById(donationId);
	        // get information user
	        User user = userService.findByemail(email);

	        // create userDonation and save to database
	        UserDonation userDonation = new UserDonation(name,money,date,message,status,donation,user);
	        userDonateService.saveUserDonate(userDonation);

	        // set attribute donateSuccess
	        theModel.addFlashAttribute("donateSuccessfully", "DonateSuccessfully");

	        return "redirect:/home";
	        
	    }
}
