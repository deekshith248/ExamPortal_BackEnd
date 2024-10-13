package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.services.implementaion.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImpl userDeatilsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader=request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken=requestTokenHeader.substring(7);
			
			try {
				username=this.jwtUtils.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt token has expired");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
			
		}else {
			System.out.println("Token not Valid");
		}
		
//		Validating username and token retrived above
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails=this.userDeatilsService.loadUserByUsername(username);
			if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
				//token is valid
				UsernamePasswordAuthenticationToken useNamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				useNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(useNamePasswordAuthenticationToken);
			}
		}else {
			System.out.println("Token is not valid");
		}
		
		
		filterChain.doFilter(request, response);
		
	}

}
