package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.CreateCarMaintenanceRequest;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.DeleteCarMaintenanceRequest;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.UpdateCarMaintenanceRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CarMaintenance;

public interface CarMaintenanceService {
	DataResult<List<CarMaintenanceSearchListDto>> getAll();
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
	DataResult<CarMaintenance> getByCar(int carId);
	public boolean isCarExistsOnCarMaintenance(int carId);
}
