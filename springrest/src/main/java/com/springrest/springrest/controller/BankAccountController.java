package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.BankAccount;
import com.springrest.springrest.service.BankAccountService;

@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountService bs;
	
	
	@GetMapping("/accounts")
	public List<BankAccount> getAllAccounts(){
		return bs.allAccounts();
	}
	 
	@GetMapping("/account/{id}")
	public BankAccount getAccountById(@PathVariable int id) {
		return bs.getAccountById(id);
		
	}
	
	@PostMapping("/account")
	 public BankAccount addBankAccount(@RequestBody BankAccount b) {
		BankAccount bb = bs.addAccount(b);
		System.out.println("Inside bankController");
		return bb;
		
	 }
	
	@PutMapping("/account/{account_id}")
	public BankAccount updateBankAccount(@RequestBody BankAccount account, @PathVariable int account_id) {
		BankAccount updatedAccount = bs.updateAccountService(account,account_id); 
		return updatedAccount;
	}
	
	@DeleteMapping("/account/{id}")
	public void delete(@PathVariable int id) {
		System.out.println("controller");
		bs.delete(id);
	}
	

}
