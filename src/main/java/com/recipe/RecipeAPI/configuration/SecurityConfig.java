package com.recipe.RecipeAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	String[] whiteList = new String[]{
		"/api/Auth/Login"
	};

	@Bean
	public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
		return http.cors().disable()
			.authorizeRequests()
			.antMatchers(whiteList).permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().disable()
			.csrf().disable()
			.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withUsername(String.valueOf(250))
			.password("P@55W0rd%")
			.authorities("CRUD")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
}
