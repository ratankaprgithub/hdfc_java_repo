package com.hdfc.service;

import java.util.List;

import com.hdfc.dto.LoginRequestDto;
import com.hdfc.dto.LoginResponseDto;
import com.hdfc.dto.UserRequestDto;
import com.hdfc.dto.UserResponseDto;

public interface UserService {

	UserResponseDto registerUser(UserRequestDto dto);

	UserResponseDto getUserById(Long id);

	List<UserResponseDto> getAllUsers();

	UserResponseDto updateUser(Long id, UserRequestDto dto);

	void deleteUser(Long id);
	
	LoginResponseDto login(LoginRequestDto dto);

}
