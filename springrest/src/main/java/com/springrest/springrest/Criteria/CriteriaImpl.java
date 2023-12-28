package com.springrest.springrest.Criteria;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.dao.EmployeeDaoImpl;
import com.springrest.springrest.entities.BankAccount;
import com.springrest.springrest.entities.Employee;

public class CriteriaImpl {

	@Autowired
	private SessionFactory sf ;
	
	public void CriteriaImpl(SessionFactory sf) {
		this.sf =  sf;
	}

	public void output() {
       try(Session s = sf.openSession()){
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Long> criteriaQuery =	builder.createQuery(Long.class);
		    Root<Employee> root = criteriaQuery.from(Employee.class);
		    criteriaQuery.select(builder.count(root));
		    
		    Long result = s.createQuery(criteriaQuery).getSingleResult();
		    System.out.println(result);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
		
	public static void main(String[] args) {	
        
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			BankAccount b = mapper.readValue(new File("C:\\Users\\EP_ITDEV_LAP_0018\\Downloads\\springrest\\springrest\\springrest\\target\\account.json"), BankAccount.class);
			
			 System.out.println(b.getAccountNumber());
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	        
	}
}


