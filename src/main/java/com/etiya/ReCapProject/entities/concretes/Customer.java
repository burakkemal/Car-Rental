package com.etiya.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends  User {
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch=FetchType.LAZY)
	List<CreditCard> creditCards;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	List<Invoice> invoices;
}
