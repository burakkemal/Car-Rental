package com.etiya.ReCapProject.business.requests.paymentRequests;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

	private double price;

	@NotNull
	private String expirationDate;

	@NotNull
	@NotBlank
	private String cardNumber;
	@NotNull
	@NotBlank
	private String cardName;
	@NotNull
	@NotBlank
	@Size(min = 3,max=3)
	private String cvv;
	

}
