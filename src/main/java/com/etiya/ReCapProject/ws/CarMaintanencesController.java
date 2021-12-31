package com.etiya.ReCapProject.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarMaintenanceService;
import com.etiya.ReCapProject.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.CreateCarMaintenanceRequest;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.DeleteCarMaintenanceRequest;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.UpdateCarMaintenanceRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

@RestController
@RequestMapping("api/carMaintenance")
public class CarMaintanencesController {

	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintanencesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("getAll")
	public DataResult<List<CarMaintenanceSearchListDto>> getAll() {
		return this.carMaintenanceService.getAll();
	}
	@DeleteMapping("delete")
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody UpdateCarMaintenanceRequest updateCarMaintenanceRequest){
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	@PostMapping("add")
	public Result add(@RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest){
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}

}

	
