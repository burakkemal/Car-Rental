package com.etiya.ReCapProject.business.requests.creditCardRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

	@JsonIgnore
	private int id;

	//@DateTimeFormat(pattern = "MM/yy")
	//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM/yy")
	//@DateTimeFormat(pattern = "MM/yy")
	@NotNull
	//@JsonFormat(pattern = "MM/yyyy")
	//@JsonDeserialize(using = LocalDateDeserializer.class)
	//@JsonSerialize(using = LocalDateSerializer.class)
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
	
	@NotNull
	private int customerId;

}
