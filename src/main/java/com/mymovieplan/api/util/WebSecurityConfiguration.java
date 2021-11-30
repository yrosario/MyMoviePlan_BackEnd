package com.mymovieplan.api.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/user/register").permitAll()
        .antMatchers(HttpMethod.GET,"/user/list").permitAll()
         .antMatchers(HttpMethod.GET,"/user/").permitAll()
         .antMatchers(HttpMethod.PUT,"/user/update").permitAll()
         .antMatchers(HttpMethod.POST,"/user/change_password").permitAll()
         .antMatchers(HttpMethod.DELETE,"/user/delete").permitAll()
         .antMatchers(HttpMethod.GET,"/movie/list").permitAll()
         .antMatchers(HttpMethod.GET,"/movie/**").permitAll()
         .antMatchers(HttpMethod.POST,"/movie/add").permitAll()
         .antMatchers(HttpMethod.PUT,"/movie/update").permitAll()
         .antMatchers(HttpMethod.DELETE,"/movie/delete").permitAll()
         .antMatchers(HttpMethod.GET,"/cart/user/**").permitAll()
         .antMatchers(HttpMethod.POST,"/cart/user/**").permitAll()
         .antMatchers(HttpMethod.DELETE,"/cart/**/").permitAll()
         .antMatchers(HttpMethod.GET,"/purchase/user/**").permitAll()
         .antMatchers(HttpMethod.POST,"/purchase/user/**").permitAll()
         .antMatchers(HttpMethod.DELETE,"/purchase/user/**").permitAll()
         .antMatchers(HttpMethod.GET,"/payment/user/**").permitAll()
         .antMatchers(HttpMethod.GET,"/payment/**/user/**").permitAll()
         .antMatchers(HttpMethod.POST,"/payment/user/**").permitAll()
         .antMatchers(HttpMethod.DELETE,"/payment/user/**").permitAll()
         .antMatchers(HttpMethod.PUT,"/payment/user/**").permitAll()
         .antMatchers(HttpMethod.GET,"/category").permitAll()
         .antMatchers(HttpMethod.POST,"/category").permitAll()
         .antMatchers(HttpMethod.GET,"/category/**").permitAll()
         .antMatchers(HttpMethod.PUT,"/category").permitAll()
         .antMatchers(HttpMethod.DELETE,"/category").permitAll()
        .anyRequest().authenticated();
    }
}