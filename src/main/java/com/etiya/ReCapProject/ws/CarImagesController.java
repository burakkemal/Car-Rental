package com.etiya.ReCapProject.ws;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.dtos.CarImageSearchListDto;
import com.etiya.ReCapProject.business.requests.carImageRequests.CreateCarImageRequest;
import com.etiya.ReCapProject.business.requests.carImageRequests.DeleteCarImageRequest;
import com.etiya.ReCapProject.business.requests.carImageRequests.UpdateCarImageRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

@RestController
@RequestMapping("api/carImages")
public class CarImagesController {
	
	private CarImageService carImageService;
	@Autowired
	public CarImagesController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}
	
	@PutMapping("update")
	public Result update(@RequestParam("carId") int carId,@RequestParam("id") int id , MultipartFile file) throws IOException {
		UpdateCarImageRequest updateCarImageRequest=new UpdateCarImageRequest();
		updateCarImageRequest.setId(id);
		updateCarImageRequest.setCarId(carId);		
		updateCarImageRequest.setFile(file);
		return this.carImageService.update(updateCarImageRequest);
	}
	
	@PostMapping("/add")
	public Result add(@RequestParam("carId") @Valid int carId, MultipartFile file) throws IOException {
		CreateCarImageRequest createCarImage = new CreateCarImageRequest();
		createCarImage.setCarId(carId);
		createCarImage.setFile(file);
		return this.carImageService.add(createCarImage);

	}
	
	@GetMapping("getall")
	public DataResult<List<CarImageSearchListDto>> getAll(){
		return this.carImageService.getAll();
	}
	
	@GetMapping("getCarImagesByCarId")
	public DataResult<List<CarImageSearchListDto>> getCarImagesByCarId(int id){
		return this.carImageService.getCarImagesByCarId(id);
	}

	@DeleteMapping("delete")
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) throws IOException {
		return this.carImageService.delete(deleteCarImageRequest);
	}
}
