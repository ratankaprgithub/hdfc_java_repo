package com.hdfc.mapper;

import com.hdfc.dto.UserRequestDto;
import com.hdfc.dto.UserResponseDto;
import com.hdfc.entity.User;

public class UserMapper {

	 public static UserResponseDto mapToUserResponseDto(User user){

		 if(user == null){
	        	return null;
	    	 }

       return UserResponseDto
               .builder()
               .id(user.getId())
               .name(user.getName())
               .email(user.getEmail())
               .role(user.getRole())
               .build();
   }
	 
	 public static User mapToUserEntity(UserRequestDto dto){

		 if(dto == null){
     		return null;
 		 }

		    return User.builder()
		            .name(dto.getName())
		            .email(dto.getEmail())
		            .password(dto.getPassword())
		            .role(dto.getRole())
		            .build();
		} 
	 
	 
}
