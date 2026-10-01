package com.hdfc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.CustomerResponseDTO;
import com.hdfc.entity.Customer;
import com.hdfc.repository.CustomerRepository;

@RestController
public class LoginController {

	
	private CustomerRepository customerRepository;

	public LoginController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	
	@GetMapping("/signIn")
	public ResponseEntity<CustomerResponseDTO> getLoggedInCustomerDetails(Authentication auth){
		
		
		System.out.println(auth);
		
		Customer customer= customerRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or Password"));
		
		CustomerResponseDTO responseDto = new CustomerResponseDTO();
		responseDto.setCustId(customer.getCustId());
		responseDto.setName(customer.getName());
		responseDto.setEmail(customer.getEmail());
		responseDto.setAddress(customer.getAddress());

		return ResponseEntity.ok(responseDto);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
