package com.assignment.review.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*
	 * @Value("${reviewUser}") private String reviewUser;
	 * 
	 * @Value("${reviewUserkey}") private String reviewUserkey;
	 */
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("test").password(encoder.encode("test")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll()
		.anyRequest().authenticated();
		http.httpBasic();
		http.csrf().disable();
		
	}

}
