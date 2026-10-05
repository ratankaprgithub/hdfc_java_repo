package com.hdfc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hdfc.security.JwtAuthenticationFilter;

@Configuration
public class AppConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	public AppConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	
	@Bean
	SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {

		
		
		http.
		sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> {

			auth
			.requestMatchers(HttpMethod.POST, "/customers").permitAll()
			.requestMatchers(HttpMethod.POST, "/signIn").permitAll()
			.requestMatchers(HttpMethod.GET, "/customers").hasRole("ADMIN")
			.requestMatchers(HttpMethod.GET,"/customers/**").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated();

		})
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		.csrf(csrf -> csrf.disable());
		//.formLogin(Customizer.withDefaults())
		//.httpBasic(Customizer.withDefaults());

		return http.build();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception {

    return authenticationConfiguration.getAuthenticationManager();
}


	
}
