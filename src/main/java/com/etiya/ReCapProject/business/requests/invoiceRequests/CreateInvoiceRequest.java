package com.etiya.ReCapProject.business.requests.invoiceRequests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.UniqueConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	@JsonIgnore
	private int id;

	@JsonIgnore
	private String invoiceNumber;
	
	@JsonIgnore
	private LocalDate createDate;

	@JsonIgnore
	private LocalDate rentDate;

	@JsonIgnore
	private int totalRentDay;

	@JsonIgnore
	private LocalDate returnDate;

	@JsonIgnore
	private double totalAmount;

	@JsonIgnore
	private int customerId;

	private int rentalId;


}
