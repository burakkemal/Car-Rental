package com.etiya.ReCapProject.business.requests.carRequests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	private int id;

	@NotNull
	int colorId;

	@NotNull
	private int brandId;

	@NotNull
	@Min(10)
	private double dailyPrice;

	@NotNull
	@Min(0)
	@Max(1900)
	private int findexScore;

	@NotNull
	@Size(min = 1900,max = 2022)
	private int modelYear;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 20)
	private String description;


	private int cityId;

	@NotNull
	@Min(0)
	@Max(250000)
	private int kilometer;
}
