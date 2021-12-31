package com.etiya.ReCapProject.business.concretes;

import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.CitySearchListDto;
import com.etiya.ReCapProject.business.requests.cityRequests.CreateCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.*;
import com.etiya.ReCapProject.dataAccess.abstracts.CityDao;
import com.etiya.ReCapProject.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CitySearchListDto>> getAll() {
        List<City> result = this.cityDao.findAll();
        List<CitySearchListDto> response = result.stream().map(city -> modelMapperService.forDto().map(city, CitySearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CitySearchListDto>>(response, Messages.CITYLIST);
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        var result = BusinessRules.run(isCityNameExists(createCityRequest.getCityName()));
        if (result != null) {
            return result;
        }
        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult(Messages.CITYADD);
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        var result = BusinessRules.run(isCityIdExist(deleteCityRequest.getId()));
        if (result != null) {
            return result;
        }
        City city = modelMapperService.forRequest().map(deleteCityRequest, City.class);
        this.cityDao.delete(city);
        return new SuccessResult(Messages.CITYDELETE);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequests) {
        var result = BusinessRules.run(isCityIdExist(updateCityRequests.getId()), isCityNameExists(updateCityRequests.getCityName()));
        if (result != null) {
            return result;
        }
        City city = modelMapperService.forRequest().map(updateCityRequests, City.class);
        this.cityDao.save(city);
        return new SuccessResult(Messages.CITYUPDATE);
    }

    @Override
    public DataResult<CitySearchListDto> getById(int id) {
        var existResult = this.cityDao.existsById(id);
        if (!existResult){
            return new ErrorDataResult<CitySearchListDto>(Messages.CITYNOTFOUND, null);
        }
        var result = this.cityDao.getById(id);
        CitySearchListDto response = modelMapperService.forDto().map(result, CitySearchListDto.class);
        return new SuccessDataResult(response, Messages.CITYFOUND);
    }

    @Override
    public Result isCityIdExist(int cityId) {
        var result = this.cityDao.existsById(cityId);
        if (!result) {
            return new ErrorResult(Messages.CITYNOTFOUND);
        }
        return new SuccessResult();
    }


    private Result isCityNameExists(String cityName) {
        var result = this.cityDao.existsByCityName(cityName);
        if (result) {
            return new ErrorResult(Messages.CITYEXISTS);
        }
        return new SuccessResult();
    }

}

