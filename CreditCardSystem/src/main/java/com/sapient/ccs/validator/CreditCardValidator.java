package com.sapient.ccs.validator;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sapient.ccs.exception.CreditCardException;

public class CreditCardValidator implements ConstraintValidator<ValidCreditCard, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (null == value)
			return false;
		validateNumber(value);
		validateVerificationDigit(value);
		return true;
	}

	protected void validateNumber(final String number) throws CreditCardException {
		// remove spaces from a card number
		String numberNoSpace = number.replaceAll("\\s", "");
		numberNoSpace = number.replaceAll("-", "");
		// check that a card number is 16 chars long
		if (numberNoSpace.length() > 19) {
			throw new CreditCardException("Length is invalid for card number: " + number);
		}

		// check that every card number char is a digit
		String[] numberArr = numberNoSpace.split("");
		try {
			IntStream.range(0, numberArr.length).forEach(i -> {
				Integer.parseInt(numberArr[i]);
			});
		} catch (NumberFormatException e) {
			throw new CreditCardException("Please enter a valid card number");
		}
	}

	protected void validateVerificationDigit(final String cardNumber) throws CreditCardException {
		// int array for processing the cardNumber
		int[] cardIntArray = new int[cardNumber.length()];

		for (int i = 0; i < cardNumber.length(); i++) {
			char c = cardNumber.charAt(i);
			cardIntArray[i] = Integer.parseInt("" + c);
		}

		for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
			int num = cardIntArray[i];
			num = num * 2;
			if (num > 9) {
				num = num % 10 + num / 10;
			}
			cardIntArray[i] = num;
		}

		int sum = Arrays.stream(cardIntArray).sum();

		if (sum % 10 != 0) {
			throw new CreditCardException("Details provided for card number " + cardNumber + " are invalid");
		}

	}

}
