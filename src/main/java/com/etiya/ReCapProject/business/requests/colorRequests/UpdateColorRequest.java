package com.etiya.ReCapProject.business.requests.colorRequests;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateColorRequest {
	
	@NotNull
	private int id;
	
	@NotNull
	@Size(min = 3, max = 15)
	private String colorName;
}
