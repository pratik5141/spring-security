package com.springrest.springrest.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springrest.springrest.entities.Employee;

public interface FetchingEmployeeByEmail extends JpaRepository<Employee,Integer> {
	
	@Query("select e from Employee e where e.emailId=:email")
	public Employee getEmployeeByEmail(@Param("email") String email);

}
