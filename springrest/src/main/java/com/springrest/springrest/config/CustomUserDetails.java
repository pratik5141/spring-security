package com.springrest.springrest.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springrest.springrest.entities.Employee;

public class CustomUserDetails implements UserDetails{
	
	private Employee employee;
	
	public CustomUserDetails(Employee employee) {
		super();
		this.employee = employee;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(employee.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		System.out.println(employee.getPassword());
		
		return employee.getPassword();
	}

	

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		System.out.println(employee.getEmailId());
		return employee.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
