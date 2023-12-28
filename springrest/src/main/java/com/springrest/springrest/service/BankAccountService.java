package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.entities.BankAccount;

public interface BankAccountService {
	
      public List<BankAccount> allAccounts();
      public BankAccount getAccountById(int id);
      public void delete(int id);
      public BankAccount addAccount(BankAccount b);
	  public BankAccount updateAccountService(BankAccount account, int account_id);
}
