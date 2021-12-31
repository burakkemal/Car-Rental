package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.CarDamageSearchListDto;
import com.etiya.ReCapProject.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.etiya.ReCapProject.business.requests.carDamageRequests.DeleteCarDamageRequest;
import com.etiya.ReCapProject.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {

    DataResult<List<CarDamageSearchListDto>>  getAll();
    Result add(CreateCarDamageRequest createCarDamageRequest);
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
    DataResult<CarDamageSearchListDto> getById(int id);
    DataResult<List<CarDamageSearchListDto>> getByCarId(int carId);

}
