package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.entities.Employee;

public interface EmployeeService {
    public List<Employee> getEmployees();

	public Employee getEmployee(int empId);

	public Employee addEmployee(Employee e);

	public void deleteEmployee(int empId);

	public Employee updateEmployee(Employee e);
}
