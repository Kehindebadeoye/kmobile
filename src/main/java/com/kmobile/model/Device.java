package com.kmobile.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deviceId;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String deviceName;
	
	@Column
	private String deviceSerialNumber;
	
	@Column
	@Min(value = 0,message = "price cannot be less than one")
	private Double price;
	
	@ManyToOne (cascade = CascadeType.MERGE)
	@JsonBackReference //This is the child
	@JoinColumn(name ="plan_id")
	private Plan plan;
	
	public Device() {
	}

	public Device(Long deviceId, String phoneNumber, String deviceName, String deviceSerialNumber,
			Double price, Plan plan) {
		super();
		this.deviceId = deviceId;
		this.phoneNumber = phoneNumber;
		this.deviceName = deviceName;
		this.deviceSerialNumber = deviceSerialNumber;
		this.price = price;
		this.plan = plan;
	}



	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	private void assignDevice(Plan p1) {
		this.plan = p1;
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", phoneNumber=" + phoneNumber + ", deviceName=" + deviceName
				+ ", deviceSerialNumber=" + deviceSerialNumber + ", plan=" + plan + "]";
	}
	
	
	

}
