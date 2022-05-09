package com.sapient.ccs.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> exception(MethodArgumentNotValidException exception) {
		Map<String, List<String>> errors = new HashMap<>();
		List<String> errorList = new ArrayList<String>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errorList.add(errorMessage);
		});
		errors.put("errorList", errorList);
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CreditCardException.class)
	public ResponseEntity<Object> processUnmergeException(final CreditCardException ex) {
		Map<String, List<String>> errors = new HashMap<>();
		List<String> errorList = new ArrayList<String>();
		errorList.add(ex.getLocalizedMessage());
		errors.put("errorList", errorList);
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
