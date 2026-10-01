package com.hdfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hdfc.entity.Customer;
import com.hdfc.repository.CustomerRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	
	private CustomerRepository customerRepository;
	
	public CustomerUserDetailsService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Customer> opt= customerRepository.findByEmail(username);
		
		
		if(opt.isPresent()) {
			Customer customer= opt.get();
			
			
			//Empty Authorities
			List<GrantedAuthority> authorities = new ArrayList<>();
			//authorities.add(new SimpleGrantedAuthority(customer.getRole()));
			
			
			// User class is predefined implementation of UserDetails interface
			return new User(customer.getEmail(), customer.getPassword(), authorities);
			
			//Using custom UserDetails implementation
			//return new CustomerUserDetails(customer);
			
		}
		else {
			throw new BadCredentialsException("User Details not found with username: "+username);
		}
		
		
		
		
		
	}

	
	
	
	
}
