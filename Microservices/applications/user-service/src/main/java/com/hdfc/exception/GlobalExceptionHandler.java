package com.hdfc.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

		ErrorResponse error = ErrorResponse
									.builder()
									.message(ex.getMessage())
									.statusCode(HttpStatus.CONFLICT.value())
									.timestamp(LocalDateTime.now())
									.build();

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse>handleUserNotFoundException(UserNotFoundException ex){

		    ErrorResponse error =
		            ErrorResponse.builder()
		                    .message(ex.getMessage())
		                    .statusCode(HttpStatus.NOT_FOUND.value())
		                    .timestamp(LocalDateTime.now())
		                    .build();

		    return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
	
	
	
	@ExceptionHandler(InvalidCredentialsException.class)
		public ResponseEntity<ErrorResponse>handleInvalidCredentialsException(InvalidCredentialsException ex){

		    ErrorResponse error = ErrorResponse.builder()
		                    .message(ex.getMessage())
		                    .statusCode(401)
		                    .timestamp(LocalDateTime.now())
		                    .build();

		    return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
		}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){

			    Map<String,String> errors = new HashMap<>();

			    ex.getBindingResult()
			      .getFieldErrors()
			      .forEach(error -> {
			          errors.put(
			             error.getField(),
			             error.getDefaultMessage());
			      });

			    return new ResponseEntity<>(
			            errors,
			            HttpStatus.BAD_REQUEST);
			}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex){

	    ErrorResponse error =
	            ErrorResponse.builder()
	                    .message(ex.getMessage())
	                    .statusCode(500)
	                    .timestamp(LocalDateTime.now())
	                    .build();

	    return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
