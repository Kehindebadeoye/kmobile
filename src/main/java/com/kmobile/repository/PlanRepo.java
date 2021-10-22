package com.kmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmobile.model.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Long>{

}
