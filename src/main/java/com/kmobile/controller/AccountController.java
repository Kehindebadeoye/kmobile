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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmobile.model.Account;
import com.kmobile.repository.AccountRepo;
import com.kmobile.service.AccountService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = ("*"))
public class AccountController {
	
	
	@Autowired
	private AccountService acctService;
	@Autowired
	private AccountRepo acctRepo;
	
	@PostMapping("/account")
	public ResponseEntity<Account> save(Account account){
		Account acct = acctService.saveAccount(account);
		return new ResponseEntity<Account>(acct, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts(){
		return ResponseEntity.ok(acctService.findAllAccount());
	}
	
	@PutMapping("/account/{accountId}")
	public ResponseEntity<Account> updateAccount(Account account, Long accountId){
		if((account.getAccountId() == accountId) && acctRepo.findById(account.getAccountId()).isPresent()) {
			acctRepo.save(account);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return null;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Account> getUserAccount(Account account) {
		List<Account> accounts = acctService.findAllAccount();
		for(Account acct : accounts) {
			System.out.println(acct.getEmail());
			if(acct.getEmail().equals(account.getEmail()) && acct.getPassword().equals(account.getPassword())) {
				return new ResponseEntity<Account>(acct, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Account>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
		acctRepo.deleteById(accountId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
