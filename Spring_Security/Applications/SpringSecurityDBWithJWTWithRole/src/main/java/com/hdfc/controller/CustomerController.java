package com.hdfc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.CustomerRequestDTO;
import com.hdfc.dto.CustomerResponseDTO;
import com.hdfc.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	
	private CustomerService customerService;
	private PasswordEncoder passEncoder;
	
	
	public CustomerController(CustomerService customerService, PasswordEncoder passEncoder) {
		this.customerService = customerService;
		this.passEncoder = passEncoder;
	}
	
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Welcome to Spring security..";
	}
	
	
	@PostMapping
	public ResponseEntity<CustomerResponseDTO> saveCustomer(@RequestBody CustomerRequestDTO customerReq){
		
		customerReq.setPassword(passEncoder.encode(customerReq.getPassword())); 
		customerReq.setRole("ROLE_"+customerReq.getRole().toUpperCase()); // admin: ROLE_ADMIN

		
		CustomerResponseDTO customerResponse= customerService.registerCustomer(customerReq);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
	}
	
	
	@GetMapping("/{email}")
	public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable String email){
		
		CustomerResponseDTO customerResponse = customerService.getCustomerDetailsByEmail(email);
		
		return ResponseEntity.ok(customerResponse);
		
	}
	
	
	 
	@GetMapping
	public ResponseEntity<List<CustomerResponseDTO>> getAllCustomerDetails(){
		
		List<CustomerResponseDTO> customers= customerService.getAllCustomerDetails();
		
		return ResponseEntity.ok(customers);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
