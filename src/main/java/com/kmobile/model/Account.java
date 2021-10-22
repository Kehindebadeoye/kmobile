package com.kmobile.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@JsonManagedReference 
	@OneToMany (mappedBy = "account", cascade = CascadeType.MERGE)
	@Column
	private List<Plan> plans = new ArrayList<Plan>();
	
	@NotNull
	@Column
	private String email;
	
	@NotNull
	@Column
	private String Password;
	
	public Account() {
		
	}

	public Account(Long accountId, String email, String password) {
		super();
		this.accountId = accountId;
		this.email = email;
		Password = password;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public List<Plan> getPlans() {
		return plans;
	}

//	public void setPlans(List<Plan> plans) {
//		this.plans = plans;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	

}
