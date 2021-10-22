package com.kmobile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmobile.model.Account;
import com.kmobile.repository.AccountRepo;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepo accountRepo;

	public Account findAccountById(Long accountId) {
		Optional<Account> optional = accountRepo.findById(accountId);
		return optional.isPresent()? optional.get(): null;
	}

	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepo.save(account);
	}

	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}
	
	

}
