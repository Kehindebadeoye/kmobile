package com.kmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmobile.model.Device;
import com.kmobile.model.Plan;
import com.kmobile.repository.DeviceRepo;
import com.kmobile.service.DeviceService;
import com.kmobile.service.PlanService;




@RequestMapping("/devices")
@RestController
@CrossOrigin (origins = ("*"))
public class DeviceController {
	
	@Autowired
	private DeviceService devService;
	@Autowired
	private DeviceRepo devRepo;
	@Autowired
	private PlanService planService;
	
	@GetMapping
	public ResponseEntity<List<Device>> findallDevices(){
		return ResponseEntity.ok(devRepo.findAll());
	}
	@PostMapping("/device")
	public ResponseEntity<Device> saveDevice(@RequestBody Device device){
		Device dev = devRepo.save(device);
		return new ResponseEntity<Device>(dev, HttpStatus.CREATED);
	}
	
	@GetMapping("/device/{deviceId}")
	public Device findDevicebyId(@PathVariable Long deviceId) {
		return devService.findDeviceById(deviceId);
	}
	
	@GetMapping("/device/generateNumber")
	public String generateNumber() {
		return devService.generateNumber();
	}
	
	@PutMapping("{deviceId}/addDevice/{planId}")
	public Plan addDeviceToPlan(@PathVariable Long deviceId,@PathVariable Long planId) {
		Device device = devService.findDeviceById(deviceId);
		Plan plan = planService.findPlanById(planId);
		devService.addDeviceToPlan(device, plan);
		return planService.findPlanById(planId);
	}
	@DeleteMapping ("/device/{deciceId}")
	public ResponseEntity<Void> deleteDeviceById(@PathVariable Long deviceId) {
		devRepo.deleteById(deviceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
