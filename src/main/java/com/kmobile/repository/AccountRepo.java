package com.kmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmobile.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{

}
