package com.springrest.springrest.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.entities.BankAccount;

public class JSONObjectMapper {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		
		BankAccount b = new BankAccount(10,1000,"Credit",999);
        try {
			mapper.writeValue(new File("C:\\Users\\EP_ITDEV_LAP_0018\\Downloads\\springrest\\springrest\\springrest\\target\\account.json"), b);
		} catch (StreamWriteException e) {
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
