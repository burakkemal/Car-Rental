package com.etiya.ReCapProject.business.requests.invoiceRequests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	
	private int id;
	
	private String invoiceNumber;
	
	@JsonIgnore
	private LocalDate createDate;
	
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	private double totalAmount;
	
	private int customerId;

	private int rentalId;


}
