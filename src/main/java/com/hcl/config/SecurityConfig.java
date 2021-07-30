package com.hcl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("Phisa")
		.password("123")
		.roles("USER")
		.and()
		.withUser("Emily")
		.password("123")
		.roles("ADMIN");
		
		//password must always be hashed
		
	}
	
	@Bean
	public PasswordEncoder getpwdEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http)throws Exception{
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/display").hasAnyRole("USER","ADMIN").and().formLogin();
	}

}