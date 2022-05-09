package com.sapient.ccs;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.ccs.entity.CreditCard;
import com.sapient.ccs.service.CreditCardService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreditCardSystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CreditCardService creditCardService;

	private static final String TEST_NAME = "Test Name";
	private static final String TEST_CARD_NUMBER = "12345678903555";

	private static final String HOME_URL = "/api/v1/home";
	private static final String ADD_URL = "/api/v1/add";

	@Test
	public void test1_getHome() throws Exception {
		CreditCard creditCard = new CreditCard();
		creditCard.setName(TEST_NAME);
		creditCard.setCardNumber(TEST_CARD_NUMBER);
		creditCard.setCardLimit(10);
		List<CreditCard> listOfCards = new ArrayList<>();
		listOfCards.add(creditCard);

		when(creditCardService.findAllCreditCards()).thenReturn(listOfCards);

		String uri = HOME_URL;
		mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void test2_add() throws Exception {
		String uri = ADD_URL;
		CreditCard creditCard = new CreditCard();
		creditCard.setName(TEST_NAME);
		creditCard.setCardNumber(TEST_CARD_NUMBER);
		creditCard.setCardLimit(10);

		when(creditCardService.addCreditCard(creditCard)).thenReturn(true);

		String inputJson = mapToJson(creditCard);
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andDo(print()).andExpect(status().isCreated());
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
