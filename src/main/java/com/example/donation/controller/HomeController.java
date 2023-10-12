package com.example.donation.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.donation.entity.Donation;
import com.example.donation.service.DonationService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@Controller
public class HomeController {

	@Autowired
	DonationService donationservice;
	
	@RequestMapping("/home")
	public String getHome(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
		
		

		Page<Donation> pages = donationservice.findAll(page);
		List<Donation> donations = pages.getContent();
		model.addAttribute("totalPage", pages.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("donations", donations);
		return "public/view/home";
	}

}