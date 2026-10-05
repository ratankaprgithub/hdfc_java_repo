package com.hdfc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.LoginRequestDTO;
import com.hdfc.dto.LoginResponseDTO;
import com.hdfc.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@PostMapping("/signIn")
	public ResponseEntity<LoginResponseDTO> loginHandler(@RequestBody LoginRequestDTO loginBean) {

		Authentication unAuth = new UsernamePasswordAuthenticationToken(loginBean.getUsername(),
				loginBean.getPassword());

		Authentication auth = authenticationManager.authenticate(unAuth);

		System.out.println(auth);

		if (auth.isAuthenticated()) {

			String token = jwtUtil.generateToken(auth);

			return ResponseEntity.ok(new LoginResponseDTO(token));

		}

		throw new BadCredentialsException("Invalid username or password..");

	}

}
