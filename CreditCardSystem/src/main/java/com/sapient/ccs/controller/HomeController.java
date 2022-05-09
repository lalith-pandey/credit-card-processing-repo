package com.sapient.ccs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.ccs.entity.CreditCard;
import com.sapient.ccs.service.CreditCardService;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
@RequestMapping("/api/v1")
public class HomeController {

	@Autowired
	private CreditCardService creditCardService;

	private static final String CARDS_LIST = "creditCardsList";
	private static final String MESSAGE = "message";
	private static final String SAVE_SUCCESS = "New credit card details added successfully.";
	private static final String SAVE_ERROR = "Error in saving credit card details, please try again.";

	@GetMapping("/home")
	private ResponseEntity<List<CreditCard>> getHomePage() {
		List<CreditCard> creditCardsList = creditCardService.findAllCreditCards();
		return ResponseEntity.ok(creditCardsList);

	}

	@PostMapping("/add")
	private ResponseEntity<Map<String, Object>> addCreditCard(@Valid @RequestBody CreditCard creditCard) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		boolean isSaveSuccess = creditCardService.addCreditCard(creditCard);
		List<CreditCard> creditCardsList = creditCardService.findAllCreditCards();

		if (isSaveSuccess) {
			responseMap.put(MESSAGE, SAVE_SUCCESS);
			responseMap.put(CARDS_LIST, creditCardsList);
			return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
		} else {
			responseMap.put(MESSAGE, SAVE_ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
		}

	}

}
