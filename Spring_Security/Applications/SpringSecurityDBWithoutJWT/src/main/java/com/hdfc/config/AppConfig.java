package com.hdfc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

	@Bean
	SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> {

			auth.requestMatchers(HttpMethod.POST, "/customers").permitAll().anyRequest().authenticated();

		}).csrf(csrf -> csrf.disable()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
