package com.kmobile.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kmobile.model.Device;
import com.kmobile.model.Plan;
import com.kmobile.repository.DeviceRepo;

@Service
public class DeviceService {
	
	@Autowired
	private DeviceRepo devRepo;

	public String generateNumber() {
		Random r = new Random();
		String phoneNumber = "";
		do {
			phoneNumber += r.nextInt(9)+1;
			for(int i=0;i<9;i++) {
				phoneNumber +=r.nextInt(10);
			}
			System.out.println(phoneNumber);
		}while(isNumberInUse(phoneNumber));
		return phoneNumber;
	}

	private boolean isNumberInUse(String phoneNumber) {
		// TODO Auto-generated method stub
		return devRepo.findByPhoneNumber(phoneNumber).isPresent();
	}

	public Device findDeviceById(Long deviceId) {
		Optional<Device> optional = devRepo.findById(deviceId);
		return optional.isPresent()? optional.get(): null;
	}

	public Device addDeviceToPlan(Device device, Plan plan) {
		device.setPlan(plan);
		return devRepo.save(device);
		
	}

}
