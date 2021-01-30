package com.su.security.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * For configuring the end users recognized by this Authorization Server
 */
@Slf4j
@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {

	@Bean
	PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.mvcMatchers("/actuator/**").permitAll()
				.mvcMatchers("/oauth/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.csrf().ignoringRequestMatchers((request) -> "/introspect".equals(request.getRequestURI()));
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}


