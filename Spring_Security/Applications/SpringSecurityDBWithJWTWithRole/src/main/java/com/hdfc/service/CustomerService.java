package com.hdfc.service;

import java.util.List;

import com.hdfc.dto.CustomerRequestDTO;
import com.hdfc.dto.CustomerResponseDTO;

public interface CustomerService {

	public CustomerResponseDTO  registerCustomer(CustomerRequestDTO customer);
	
	public CustomerResponseDTO getCustomerDetailsByEmail(String email);
	
	public List<CustomerResponseDTO> getAllCustomerDetails();
	
	
	
}
