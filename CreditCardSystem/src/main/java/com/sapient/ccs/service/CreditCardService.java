package com.sapient.ccs.service;

import java.util.List;

import com.sapient.ccs.entity.CreditCard;

public interface CreditCardService {

	List<CreditCard> findAllCreditCards();

	boolean addCreditCard(CreditCard creditCard);

}
