package com.kmobile.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.kmobile.model.Account;
import com.kmobile.model.Plan;
import com.kmobile.service.AccountService;
import com.kmobile.service.PlanService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/plans")
public class PlanController {
	
	@Autowired
	private PlanService service;
	
	@Autowired
	private AccountService acctService;
	
	
	
	@PostMapping("/plan")
	public ResponseEntity<Plan> save(@RequestBody @Valid Plan plan, Account account){
		Plan p = service.savePlan(plan);
		return new ResponseEntity<Plan>(p,HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Plan>> getAllPlans(){
		return ResponseEntity.ok(service.findAllPlans());
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> getPlanById(@PathVariable ("planId")Long planId){
		if(planId !=null) {
			Optional<Plan> optional = Optional.of(service.findPlanById(planId));
			return optional.isPresent()? ResponseEntity.ok(optional.get()): ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Plan>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("{planId}/addplan/{accountId}")
	public Account addPlanToAccount(@PathVariable Long planId, @PathVariable Long accountId) {
		Plan p = service.findPlanById(planId);
		Account a = acctService.findAccountById(accountId);
		service.addPlanToAccount(p, a);
		return acctService.findAccountById(accountId);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<Void> deletePlanById(Long planId) {
		service.deletePlanById(planId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
