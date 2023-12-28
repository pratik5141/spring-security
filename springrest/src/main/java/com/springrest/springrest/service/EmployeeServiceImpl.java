package com.springrest.springrest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.EmployeeDao;
import com.springrest.springrest.entities.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
//	List<Employee> list;
	@Autowired
	private EmployeeDao empDao;
	
	
	public EmployeeServiceImpl() {
		/*
		 * list = new ArrayList<>();
		 * 
		 * list.add(new Employee(1,"Nitin",75000)); list.add(new
		 * Employee(2,"Pratik",50000));
		 */
		 
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		
		List<Employee> list = empDao.findAll();
		return list;
	}

	@Override
	public Employee getEmployee(int empId) {
		// TODO Auto-generated method stub
		
		/*
		 * Employee emp = null;; for(Employee e:list) { if(e.getEmpId() == empId) { emp
		 * = e; break; } }
		 */
		//Employee emp = empDao.findById(empId).get();
		Employee e = empDao.findById(empId);
		return e;
	}

	@Override
	public Employee addEmployee(Employee e) {
		System.out.println("Inside emp service"+e.toString());
		Employee ee = empDao.addEmployee(e);
		return ee;
	}

	@Override
	public void deleteEmployee(int empId) {
		//Employee e = empDao.findById(empId).get();
		/*
		 * for(Employee emp:list) { if(emp.getEmpId() == empId) { list.remove(empId); e
		 * = emp; break; } }
		 */
		//empDao.delete(e);
		empDao.deleteById(empId);
		
	}

	@Override
	public Employee updateEmployee(Employee e) {
		
		/*
		 * for(Employee emp:list) { if(e.getEmpId() == emp.getEmpId()) {
		 * emp.setName(e.getName()); emp.setSalaty(e.getSalaty()); } }
		 */
		//empDao.save(e);
         Employee updatedEmp = empDao.updateEmployee(e);
		return updatedEmp;
	}

}
