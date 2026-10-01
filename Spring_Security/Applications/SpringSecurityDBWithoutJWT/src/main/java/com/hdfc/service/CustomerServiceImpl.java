package com.hdfc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hdfc.dto.CustomerRequestDTO;
import com.hdfc.dto.CustomerResponseDTO;
import com.hdfc.entity.Customer;
import com.hdfc.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository custRepository;

	public CustomerServiceImpl(CustomerRepository custRepository) {
		this.custRepository = custRepository;
	}

	@Override
	public CustomerResponseDTO registerCustomer(CustomerRequestDTO customer) {

		Customer c1 = new Customer();
		c1.setName(customer.getName());
		c1.setAddress(customer.getAddress());
		c1.setEmail(customer.getEmail());
		c1.setPassword(customer.getPassword());

		Customer savedCustomer = custRepository.save(c1);

		CustomerResponseDTO responseDto = new CustomerResponseDTO();
		responseDto.setCustId(savedCustomer.getCustId());
		responseDto.setName(savedCustomer.getName());
		responseDto.setEmail(savedCustomer.getEmail());
		responseDto.setAddress(savedCustomer.getAddress());

		return responseDto;

	}

	@Override
	public CustomerResponseDTO getCustomerDetailsByEmail(String email) {

		Customer customer = custRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Customer does not exist with email: " + email));

		CustomerResponseDTO responseDto = new CustomerResponseDTO();
		responseDto.setCustId(customer.getCustId());
		responseDto.setName(customer.getName());
		responseDto.setEmail(customer.getEmail());
		responseDto.setAddress(customer.getAddress());

		return responseDto;

	}

	@Override
	public List<CustomerResponseDTO> getAllCustomerDetails() {

		List<Customer> customers = custRepository.findAll();

		return customers.stream().map(c -> {

			CustomerResponseDTO responseDto = new CustomerResponseDTO();
			responseDto.setCustId(c.getCustId());
			responseDto.setName(c.getName());
			responseDto.setEmail(c.getEmail());
			responseDto.setAddress(c.getAddress());

			return responseDto;

		}).collect(Collectors.toList());

	}

}
