package com.etiya.ReCapProject.business.requests.paymentRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePaymentRequest {
	
	@NotNull
	private int id;

}

