package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.CitySearchListDto;
import com.etiya.ReCapProject.business.requests.cityRequests.CreateCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<List<CitySearchListDto>> getAll();
    Result add(CreateCityRequest createCityRequest);
    Result delete(DeleteCityRequest deleteCityRequest);
    Result update(UpdateCityRequest updateCityRequests);
    DataResult<CitySearchListDto> getById(int id);
    Result isCityIdExist(int id);
}
