package com.springrest.springrest.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redis.Authorization;
import com.springrest.springrest.entities.Employee;
import com.springrest.springrest.service.EmployeeService;
import com.springrest.springrest.service.PracticePrograms;

@RestController
public class EmployeeController {
    @Autowired
	EmployeeService empService; 
	
    @Autowired
    PracticePrograms practicePrograms;
           
	@RequestMapping("/welcome")
	public String welcome() {	
		try {
			//String token = Authorization.getInstance().getAccessToken();
			String url ="https://eqftt.equitasbank.com/ETCAPICP/api/master/getstate";
			ObjectNode data = new ObjectMapper().createObjectNode();
			data.put("RegionID",1);
			JsonNode response = Authorization.getInstance().callPostAPI(url, data);
			
			String url2 = "https://eqftt.equitasbank.com/ETCAPICP/api/master/getregion";
		//	JsonNode getResponse = Authorization.getInstance().getRequest(url2);			
			return response.asText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Welcome to Easy Pay !!!"; 
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/employees")
	public ResponseEntity<List<Employee>> getEmployess(){
		List<Employee> list = this.empService.getEmployees();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/get/employee/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int empId) {
		Employee e = this.empService.getEmployee(empId);
		
		/*
		 * if(e==null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
		 */
		return ResponseEntity.of(Optional.of(e));
	}
	
	
	@PostMapping("/register")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {
		Employee emp = null;
		System.out.println("Inside emp controller"+e.toString());
		try {
		emp = this.empService.addEmployee(e); 
        return ResponseEntity.of(Optional.of(emp));
		}
		catch(Exception el){
			el.getStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/employee/{empId}")
	//public ResponseEntity<Employee> deleteEmployee(@PathVariable int empId) {
	public void deleteEmployee(@PathVariable int empId) {
		 this.empService.deleteEmployee(empId);
		//return ResponseEntity.of(Optional.of(e));
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e) {
		Employee ee = this.empService.updateEmployee(e);
		return ResponseEntity.of(Optional.of(ee));
	}
	
	
	  @ResponseStatus(value=HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(Exception.class) 
	  public String genericExceptionHandler() {
	  return "OOPS!!! Something went wrong"; }
	 
}
