package com.mymovieplan.api.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


import com.mymovieplan.api.filter.CustomAuthorizationFilter;
import com.mymovieplan.api.filter.Filter;


@Configuration @EnableWebSecurity @Component
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public WebSecurityConfiguration(UserDetailsService userDetailsService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

  
	}
	
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/movie/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/token/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/movie/**").hasAuthority("ROLE_ADMIN");
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.PUT, "/user/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ROLE_USER");
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyAuthority("ROLE_ADMIN");
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasAnyAuthority("ROLE_ADMIN");
    	http.authorizeRequests().antMatchers(HttpMethod.PUT, "/**").hasAnyAuthority("ROLE_ADMIN");
    	http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority("ROLE_ADMIN");
    	http.authorizeRequests().anyRequest().authenticated();
    	http.addFilter(new Filter(authenticationManagerBean()));
    	http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
    	
    	return super.authenticationManager();
    }
}