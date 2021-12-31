package com.etiya.ReCapProject.entities.concretes;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "invoice_number",unique = true)
	private String invoiceNumber;

	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "rent_date")
	private LocalDate rentDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "total_rent_day")
	private int totalRentDay;

	@Column(name = "total_amount")
	private double totalAmount;

	@OneToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;



}
