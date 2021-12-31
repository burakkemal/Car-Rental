package com.etiya.ReCapProject.business.requests.carMaintenanceRequess;


import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@JsonIgnore
	private int id;
	
	@NotNull
	private int car_Id;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String description;

	@JsonIgnore
	private LocalDate returnDate;

}
