package com.springrest.springrest.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springrest.springrest.Utils.JwtUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Lazy
	@Autowired
	private UserDetailsServiceImpl userDeatilsServiceImplObject;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get
		//Bearer start or not 
		//validate 
		//send token
		
		String reqTokenHeader = request.getHeader("Authorization");
		String email = null;
		String jwtToken=null;
		//null and format of token check
		if(reqTokenHeader !=null && reqTokenHeader.startsWith("Bearer") ) {
			jwtToken = reqTokenHeader.substring(7);
			try {
				email = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Security -- null check --
			UserDetails userFromJwt = userDeatilsServiceImplObject.loadUserByUsername(email);
			
			if(email !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userFromJwt,null, userFromJwt.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}			
		}
	 filterChain.doFilter(request, response);
		
	}

}
