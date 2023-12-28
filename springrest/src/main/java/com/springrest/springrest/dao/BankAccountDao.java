package com.springrest.springrest.dao;

import java.util.List;

import com.springrest.springrest.entities.BankAccount;

public interface BankAccountDao {
	public List<BankAccount> allAccounts();
    public BankAccount getAccountById(int id);
    public void delete(int id);
    public BankAccount addAccount(BankAccount b);
	public BankAccount updateAccountDao(BankAccount account, int account_id);
}
