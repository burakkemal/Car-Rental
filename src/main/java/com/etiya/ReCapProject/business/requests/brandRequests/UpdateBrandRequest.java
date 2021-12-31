package com.etiya.ReCapProject.business.requests.brandRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	@NotNull
	private int id;
	@NotNull
	@Size(min = 3, max = 15)
	private String brandName;
}
