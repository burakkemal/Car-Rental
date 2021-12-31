package com.etiya.ReCapProject.business.requests.colorRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {
	
	@NotNull
	@Size(min = 3, max = 15)
	private String colorName;
	

}
