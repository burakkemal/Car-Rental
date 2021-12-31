package com.etiya.ReCapProject.entities.concretes.complexTypes;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandDetail {
	private int id;
	private int brandId;
	private String description;
}
