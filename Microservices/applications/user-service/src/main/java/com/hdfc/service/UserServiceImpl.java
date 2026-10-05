package com.hdfc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hdfc.dto.LoginRequestDto;
import com.hdfc.dto.LoginResponseDto;
import com.hdfc.dto.UserRequestDto;
import com.hdfc.dto.UserResponseDto;
import com.hdfc.entity.User;
import com.hdfc.exception.InvalidCredentialsException;
import com.hdfc.exception.UserAlreadyExistsException;
import com.hdfc.exception.UserNotFoundException;
import com.hdfc.mapper.UserMapper;
import com.hdfc.repository.UserRepository;
import com.hdfc.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;
	
	private final JwtUtil jwtUtil;

	@Override
	public UserResponseDto registerUser(UserRequestDto dto) {

		Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());

		if (existingUser.isPresent()) {

			throw new UserAlreadyExistsException("Email already exists");
		}

		User user = UserMapper.mapToUserEntity(dto);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(user);

		return UserMapper.mapToUserResponseDto(savedUser);
	}

	@Override
	public UserResponseDto getUserById(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		return UserMapper.mapToUserResponseDto(user);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {

		return userRepository.findAll().stream().map(UserMapper::mapToUserResponseDto).collect(Collectors.toList());
	}

	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

		user.setName(dto.getName());

		user.setRole(dto.getRole());

		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		User updatedUser = userRepository.save(user);

		return UserMapper.mapToUserResponseDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

		userRepository.delete(user);

	}

	@Override
	public LoginResponseDto login(LoginRequestDto dto) {

		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

		boolean matches = passwordEncoder.matches(dto.getPassword(), user.getPassword());

		if (!matches) {

			throw new InvalidCredentialsException("Invalid credentials");
		}

		String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

		return LoginResponseDto.builder()
								.token(token)
								.type("Bearer")
								.email(user.getEmail())
								.role(user.getRole())
								.build();
	}
}