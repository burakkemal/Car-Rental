package com.etiya.ReCapProject.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.dtos.CarSearchListDto;
import com.etiya.ReCapProject.business.requests.carRequests.CreateCarRequest;
import com.etiya.ReCapProject.business.requests.carRequests.DeleteCarRequest;
import com.etiya.ReCapProject.business.requests.carRequests.UpdateCarRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.complexTypes.CarBrandDetail;
import com.etiya.ReCapProject.entities.concretes.complexTypes.CarColorDetail;
import com.etiya.ReCapProject.entities.concretes.complexTypes.CarDetail;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;
	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {

		return this.carService.add(createCarRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);

	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);

	}

	@GetMapping("/getAll")
	public DataResult<List<CarSearchListDto>> getAll() {
		return this.carService.getAll();
	}

	@GetMapping("/getCarWithBrandAndColorDetails")
	public DataResult<List<CarDetail>> getCarWithBrandAndColorDetails() {
		return this.carService.getCarWithBrandAndColorDetails();
	}

	@GetMapping("/getById")
	public DataResult<CarSearchListDto> getById(int id) {
		return this.carService.getById(id);
	}
	@GetMapping("/getCarByBrandId")
	public DataResult<List<CarBrandDetail>> getCarByBrandId(int brandId) {
		return this.carService.getByBrandId(brandId);
	}
	@GetMapping("/getCarByColorId")
	public DataResult<List<CarColorDetail>> getCarByColorId(int colorId) {
		return this.carService.getByColorId(colorId);
	}
	@GetMapping("/getAvailableCars")
	public DataResult<List<CarSearchListDto>> getAvailableCars() {
		return this.carService.getAvailableCars();
	}
	

}
