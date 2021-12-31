package com.etiya.ReCapProject.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSearchListDto {
	
	private int creditCartId;
	private double amount;

}
