package com.etiya.ReCapProject.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSearchListDto {
	
	private String invoiceNumber;
	private LocalDate createDate;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private double totalAmount;
	private int customerId;
}
