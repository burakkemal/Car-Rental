package com.etiya.ReCapProject.business.requests.brandRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandRequest {


	@NotNull
	@PositiveOrZero
	private int id;
}
