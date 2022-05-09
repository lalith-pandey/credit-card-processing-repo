package com.sapient.ccs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.ccs.entity.CreditCard;
import com.sapient.ccs.repository.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Override
	public List<CreditCard> findAllCreditCards() {
		return creditCardRepository.findAll();
	}

	@Override
	public boolean addCreditCard(CreditCard creditCard) {
		creditCardRepository.save(creditCard);
		return true;
	}

}
