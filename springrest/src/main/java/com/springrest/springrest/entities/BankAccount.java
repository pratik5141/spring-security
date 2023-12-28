package com.springrest.springrest.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class BankAccount implements Serializable  {
	
	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", balance=" + balance + ", transcationType=" + transcationType
				+ ", accountNumber=" + accountNumber + "]";
	}

	@Id
	@Column(name="accountd")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountId;
	@Column(name="balance")
	private long balance;
	@Column(name="transctionType")
	private String transcationType;
	@Column(name="accountNumber")
	private long accountNumber;
	
	//@OneToOne(mappedBy="bankAccount",cascade=CascadeType.ALL)
	//@JsonIgnore
	//private Employee emp;
	
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public BankAccount(int accountId, long balance, String transcationType, long accountNumber) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.transcationType = transcationType;
		this.accountNumber = accountNumber;
		//this.emp = emp;
	}



	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getTranscationType() {
		return transcationType;
	}

	public void setTranscationType(String transcationType) {
		this.transcationType = transcationType;
	}

	/*
	 * public Employee getEmp() { return emp; }
	 * 
	 * public void setEmp(Employee emp) { this.emp = emp; }
	 * 
	 * @Override public String toString() { return "BankAccount [accountId=" +
	 * accountId + ", balance=" + balance + ", transcationType=" + transcationType +
	 * ", emp=" + emp + "]"; }
	 */
}
