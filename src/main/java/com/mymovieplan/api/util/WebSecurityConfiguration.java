package com.mymovieplan.api.util;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

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
    	
    	http.csrf().disable().cors();
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/movie/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/movie/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/token/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/movie/**").hasAuthority("ROLE_ADMIN");
    	http.authorizeRequests().antMatchers(HttpMethod.PUT, "/movie/**").hasAuthority("ROLE_ADMIN");
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.PUT, "/user/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.GET, "/cart/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/cart/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.PUT, "/cart/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/cart/**").permitAll();
    	http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/user/**").permitAll();
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
    
        
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	
        final CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
        
        /*final CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        //configuration.setAllowedOrigins(Arrays.asList("**"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("X-Auth-Token", "Authorization", "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;*/
    	/*UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;*/
    }
}