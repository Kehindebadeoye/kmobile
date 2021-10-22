package com.kmobile.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planId;
	
	@NotNull
	@Column
	private String planType;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="account_id")
	private Account account;
	
	@JsonManagedReference //this is the parent
	@JsonIgnore
	@Column
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Device> devices = new HashSet<Device>();
	
	public Plan() {
		
	}
	
	public Plan(Long planId, String planType, Account account) {
		super();
		this.planId = planId;
		this.planType = planType;
		this.account = account;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Device> getDevices() {
		return devices;
	}

//	public void setDevices(Set<Device> devices) {
//		this.devices = devices;
//	}
	
	public void assignPlan(Account a) {
		this.account = a;
	}

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planType=" + planType + ", account=" + account + "]";
	}
	
	

}
