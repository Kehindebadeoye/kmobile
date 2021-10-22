package com.kmobile.service;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmobile.model.Account;
import com.kmobile.model.Plan;
import com.kmobile.repository.PlanRepo;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepo planRepo;

	public Plan savePlan(@Valid Plan plan) {
		
		return planRepo.save(plan);
	}

	public List<Plan> findAllPlans() {
		return planRepo.findAll();
	}

	public Plan findPlanById(Long planId) {
		Optional<Plan> optional = planRepo.findById(planId);
		return optional.isPresent()? optional.get(): null;
		}
	
	public Plan addPlanToAccount(Plan p, Account a) {
		p.setAccount(a);
		return planRepo.save(p);
	}

	public void deletePlanById(Long planId) {
		planRepo.deleteById(planId);
		
	}

}
