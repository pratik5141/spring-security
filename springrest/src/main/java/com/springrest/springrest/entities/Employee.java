package com.springrest.springrest.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



@Entity
public class Employee{
	
	@Id
	@Column(name="empId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int empId;
	@Column(name="empName")
    String name;
	@Column(name="salary")
    long salary;
	
	@Column(name="email_id")
	String emailId;
	@Column(name="password")
	String password;
	
	String role;
    
    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="account_id")
   // @JsonIgnore
    BankAccount bankAccount;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    Company company;
    
	/*
	 * @OneToMany List<Laptop> laptops;
	 */

	public int getEmpId() {
		return empId;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Employee(int empId, String name, long salary, String emailId, String password, String role,
			BankAccount bankAccount, Company company) {
		super();
		this.empId = empId;
		this.name = name;
		this.salary = salary;
		this.emailId = emailId;
		this.password = password;
		this.role = role;
		this.bankAccount = bankAccount;
		this.company = company;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}




	public Employee() {
		super();
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", salary=" + salary + ", emailId=" + emailId
				+ ", password=" + password + ", role=" + role + ", bankAccount=" + bankAccount + ", company=" + company
				+ "]";
	}
	
    
    
}
