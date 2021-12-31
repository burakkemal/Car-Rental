package com.etiya.ReCapProject.business.requests.colorRequests;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteColorRequest {
	
	@NotNull
	private int id;
}
