package com.etiya.ReCapProject.business.requests.brandRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

	@NotNull
	@Size(min = 3, max = 15,message = Messages.BRANDNOTFOUND)
	private String brandName;
}
