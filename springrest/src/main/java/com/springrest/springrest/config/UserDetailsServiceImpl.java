package com.springrest.springrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.springrest.springrest.dao.FetchingEmployeeByEmail;
import com.springrest.springrest.entities.Employee;

public class UserDetailsServiceImpl implements UserDetailsService{

	/*
	 * @Autowired private EmployeeDao employeeDao;
	 */
	
	@Autowired
	private FetchingEmployeeByEmail fetchEmployee;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Employee emp =  fetchEmployee.getEmployeeByEmail(email);
				
		System.out.println("inside load user...."+emp.getEmailId());
		if(emp==null) {
			throw new UsernameNotFoundException("Employee not found for loading for security");	
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(emp);
		return customUserDetails;
	}

}
