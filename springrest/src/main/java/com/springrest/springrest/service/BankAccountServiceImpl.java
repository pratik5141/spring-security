package com.springrest.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.BankAccountDao;
import com.springrest.springrest.entities.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    
	@Autowired
	BankAccountDao bd;
	BankAccount b;
	
	@Override
	public List<BankAccount> allAccounts() {
		// TODO Auto-generated method stub
		List<BankAccount> list = bd.allAccounts();
		return list;
	}

	@Override
	public BankAccount getAccountById(int id) {
		// TODO Auto-generated method stub
		 b = bd.getAccountById(id);
		return b;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		bd.delete(id);
		System.out.println("service bank");
	}

	@Override
	public BankAccount addAccount(BankAccount bInput) {
		// TODO Auto-generated method stub
		b = bd.addAccount(bInput);
		
		System.out.println("Inside bankService");
		return b;
	}

	@Override
	public BankAccount updateAccountService(BankAccount account, int account_id) {
		BankAccount updatedAccount = bd.updateAccountDao(account,account_id);
		return updatedAccount;
	}

}
