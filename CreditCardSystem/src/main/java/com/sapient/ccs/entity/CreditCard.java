package com.sapient.ccs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sapient.ccs.validator.ValidCreditCard;

import lombok.Data;

@Entity
@Table(name = "CREDIT_CARD")
@Data
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotBlank(message = "Please enter Name")
	private String name;
	@Column
	@NotBlank(message = "Please enter Card Number")
	@ValidCreditCard
	private String cardNumber;
	@Column
	private double balance;
	@Column
	private double cardLimit;

}
