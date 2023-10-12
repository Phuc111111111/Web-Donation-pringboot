package com.example.donation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_donation")
public class UserDonation {
	 @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    @Column(name="id")
	    private Integer id;

	    @Column(name="name")
	    private String name;

	    @Column(name="money")
	    private Integer money;

	    @Column(name="date")
	    private String date;

	    @Column(name="message_content")
	    private String messageContent;

	    @Column(name="status")
	    private Integer status;
	    
	    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	    @JoinColumn(name="donation_id")
	    private Donation donation;

	    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	    @JoinColumn(name="user_id")
	    private User user;
	    
	    public UserDonation() {
		}


		public UserDonation(String name, Integer money, String date, String messageContent, Integer status,
				Donation donation, User user) {
			this.name = name;
			this.money = money;
			this.date = date;
			this.messageContent = messageContent;
			this.status = status;
			this.donation = donation;
			this.user = user;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Integer getMoney() {
			return money;
		}


		public void setMoney(Integer money) {
			this.money = money;
		}


		public String getDate() {
			return date;
		}


		public void setDate(String date) {
			this.date = date;
		}


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}


		public Donation getDonation() {
			return donation;
		}


		public void setDonation(Donation donation) {
			this.donation = donation;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public String getMessageContent() {
			return messageContent;
		}


		public void setMessageContent(String messageContent) {
			this.messageContent = messageContent;
		}
		
		
	    
	    
}
