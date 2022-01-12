package com.mymovieplan.api.util;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Component;

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
    	http.authorizeRequests().anyRequest().permitAll();
    	http.addFilter(new Filter(authenticationManagerBean()));
//       http.csrf().disable().authorizeRequests()
//        .antMatchers("/").permitAll()
//        .antMatchers(HttpMethod.POST,"/user/register").permitAll()
//        .antMatchers(HttpMethod.GET,"/user/list").permitAll()
//         .antMatchers(HttpMethod.GET,"/user/").permitAll()
//         .antMatchers(HttpMethod.PUT,"/user/update").permitAll()
//         .antMatchers(HttpMethod.POST,"/user/change_password").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/user/delete").permitAll()
//         .antMatchers(HttpMethod.GET,"/movie/list").permitAll()
//         .antMatchers(HttpMethod.GET,"/movie/**").permitAll()
//         .antMatchers(HttpMethod.POST,"/movie/add").permitAll()
//         .antMatchers(HttpMethod.PUT,"/movie/update").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/movie/delete").permitAll()
//         .antMatchers(HttpMethod.GET,"/cart/user/**").permitAll()
//         .antMatchers(HttpMethod.POST,"/cart/user/**").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/cart/**/").permitAll()
//         .antMatchers(HttpMethod.GET,"/purchase/user/**").permitAll()
//         .antMatchers(HttpMethod.POST,"/purchase/user/**").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/purchase/user/**").permitAll()
//         .antMatchers(HttpMethod.GET,"/payment/user/**").permitAll()
//         .antMatchers(HttpMethod.GET,"/payment/**/user/**").permitAll()
//         .antMatchers(HttpMethod.POST,"/payment/user/**").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/payment/user/**").permitAll()
//         .antMatchers(HttpMethod.PUT,"/payment/user/**").permitAll()
//         .antMatchers(HttpMethod.GET,"/category").permitAll()
//         .antMatchers(HttpMethod.POST,"/category").permitAll()
//         .antMatchers(HttpMethod.GET,"/category/**").permitAll()
//         .antMatchers(HttpMethod.PUT,"/category").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/category").permitAll()
//         .antMatchers(HttpMethod.GET,"/movie/**/desc").permitAll()
//         .antMatchers(HttpMethod.POST,"/movie/**/desc").permitAll()
//         .antMatchers(HttpMethod.PUT,"/movie/**/desc").permitAll()
//         .antMatchers(HttpMethod.POST,"/movie/add").permitAll()
//         .antMatchers(HttpMethod.PUT,"/movie/update").permitAll()
//         .antMatchers(HttpMethod.DELETE,"/movie/delete").permitAll()
//         .antMatchers(HttpMethod.POST,"/movie/**/upload").permitAll()
//         .antMatchers(HttpMethod.GET,"/movie/**/upload").permitAll()
//        .anyRequest().authenticated();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
    	
    	return super.authenticationManager();
    }
}