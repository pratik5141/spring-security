package com.springrest.springrest.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.Utils.JwtUtil;
import com.springrest.springrest.config.UserDetailsServiceImpl;
import com.springrest.springrest.entities.JwtRequest;
import com.springrest.springrest.entities.JwtResponse;

@RestController
public class JwtSecurityController {
	
	@Autowired
	private UserDetailsService userDetails;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity<?> generateToken( HttpServletRequest request) throws Exception{
		 String upd=request.getHeader("authorization");
		 String pair=new String(Base64.decodeBase64(upd.substring(6)));
		  String userName=pair.split(":")[0];
		  String password=pair.split(":")[1];
		
		JwtRequest jwtRequest=new JwtRequest();
		
		jwtRequest.setEmail(userName);
		jwtRequest.setPassword(password);
		System.out.println(jwtRequest);
		
		
		 
		try {
			//email password validation to authManager
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));	
		}
		catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		UserDetails user = userDetails.loadUserByUsername(jwtRequest.getEmail());
		String token = this.jwtUtil.generateToken(user);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}
