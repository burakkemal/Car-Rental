package com.etiya.ReCapProject.business.requests.paymentRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

	@NotNull
	private int id;

	@NotNull
	@Min(0)
	private double amount;

	@NotNull
	private int creditCardId;
}
