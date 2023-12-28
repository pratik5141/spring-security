package com.springrest.springrest.dao;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.entities.Employee;

public interface EmployeeDao //extends JpaRepository<Employee,Integer>
{
     public List<Employee> findAll();
     public Employee findById(int empId);
     public void deleteById(int empId);
     public Employee addEmployee(Employee e);
     public Employee updateEmployee(Employee e);
     public Employee getUserByEmail(String email);
}
