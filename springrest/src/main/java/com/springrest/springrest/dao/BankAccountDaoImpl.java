package com.springrest.springrest.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springrest.springrest.entities.BankAccount;

@Repository
@Transactional
public class BankAccountDaoImpl implements BankAccountDao {

	@Autowired
	private SessionFactory sf;
	private BankAccount accountObject;
	
	
	public BankAccountDaoImpl(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public List<BankAccount> allAccounts() {
		// TODO Auto-generated method stub
		Session currentSession = sf.openSession();
		List<BankAccount> accounts = currentSession.createQuery("from BankAccount",BankAccount.class).list();
		currentSession.close();
		return accounts;
	}

	@Override
	public BankAccount getAccountById(int id) {
		Session currentSession = sf.openSession();
		accountObject = (BankAccount) currentSession.find(BankAccount.class, id);
		currentSession.close();
		return accountObject;
	}

	@Override
	public void delete(int id) {
		Session currentSession = sf.openSession();
		Transaction txn = currentSession.beginTransaction();
		
	   // accountObject = currentSession.find(BankAccount.class, id);
		System.out.println("Inside bankDao");
		Query thequery =currentSession.createQuery("delete from BankAccount where id=:accountd");
		thequery.setParameter("accountd", id);
	
		thequery.executeUpdate();
		txn.commit();
		currentSession.close();
		
	}

	@Override
	public BankAccount addAccount(BankAccount b) {
	    
		Session currentSession = sf.openSession();
		System.out.println("Inside bankDao"+b.toString());
		currentSession.saveOrUpdate(b);
		currentSession.close();
		return b;
	}

	@Override
	public BankAccount updateAccountDao(BankAccount account, int account_id) {
		
		Session currentSession = sf.openSession();
		System.out.println("inside update bank dao"+account_id);
		/*
		 * accountObject = currentSession.get(BankAccount.class, account_id);
		 * 
		 * accountObject.setAccountId(account_id);
		 * accountObject.setAccountNumber(account.getAccountNumber());
		 * accountObject.setBalance(account.getBalance());
		 * accountObject.setTranscationType(account.getTranscationType());
		 * System.out.println(accountObject.toString());
		 */
		
		//account.setAccountId(account_id);
		
		Transaction tx = currentSession.beginTransaction();
		System.out.println(account.toString());
		currentSession.update(account);	
		tx.commit();
		currentSession.close();
		return account;
	}

}
