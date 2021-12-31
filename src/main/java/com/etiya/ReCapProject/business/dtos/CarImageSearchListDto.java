package com.etiya.ReCapProject.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImageSearchListDto {
	
	private int id;
	private int carId;
	private String imagePath;
	private LocalDate imageDate;

}
