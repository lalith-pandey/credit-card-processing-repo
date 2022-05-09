package com.sapient.ccs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.ccs.entity.CreditCard;
import com.sapient.ccs.repository.CreditCardRepository;
import com.sapient.ccs.service.CreditCardService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CreditCardSystemServiceTests {

	@Autowired
	CreditCardService creditCardService;

	@Autowired
	CreditCardRepository creditCardRepository;

	private static final String TEST_NAME = "Test Name";
	private static final String TEST_CARD_NUMBER = "12345678903555";

	@Test
	public void test1_addNewCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setName(TEST_NAME);
		creditCard.setCardNumber(TEST_CARD_NUMBER);
		creditCard.setCardLimit(10);
		boolean addSuccess = creditCardService.addCreditCard(creditCard);
		assertTrue(addSuccess);
	}

	@Test
	public void test2_findAllCreditCards() {
		List<CreditCard> creditCardList = creditCardService.findAllCreditCards();
		assertEquals(creditCardList.size(), 1);
		assertEquals(creditCardList.get(0).getName(), TEST_NAME);
		assertEquals(creditCardList.get(0).getCardNumber(), TEST_CARD_NUMBER);
	}

}
