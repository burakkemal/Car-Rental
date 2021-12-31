package com.etiya.ReCapProject.business.requests.carImageRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {

	@NotNull
	private int id;
	@NotNull
	private int carId;
	@NotNull
	private String imagePath;
	@NotNull
	private LocalDate imageDate;
	@NotNull
	private MultipartFile file;

}
