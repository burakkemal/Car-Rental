package com.etiya.ReCapProject.business.requests.carImageRequests;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarImageRequest {
	
	@NotNull
	private int id;

}
