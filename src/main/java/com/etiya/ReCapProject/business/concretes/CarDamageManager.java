package com.etiya.ReCapProject.business.concretes;

import com.etiya.ReCapProject.business.abstracts.CarDamageService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.CarDamageSearchListDto;
import com.etiya.ReCapProject.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.etiya.ReCapProject.business.requests.carDamageRequests.DeleteCarDamageRequest;
import com.etiya.ReCapProject.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.*;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDamageDao;
import com.etiya.ReCapProject.entities.concretes.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {


    private ModelMapperService modelMapperService;
    private CarDamageDao carDamageDao;
    private CarService carService;

    @Autowired
    public CarDamageManager(ModelMapperService modelMapperService, CarDamageDao carDamageDao, CarService carService) {
        this.modelMapperService = modelMapperService;
        this.carDamageDao = carDamageDao;
        this.carService = carService;
    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getAll() {
        List<CarDamage> carDamages = this.carDamageDao.findAll();
        List<CarDamageSearchListDto> response = carDamages.stream().map(carDamage -> modelMapperService.forDto().map(carDamage , CarDamageSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageSearchListDto>>(response, Messages.DAMAGELIST);
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        var result = BusinessRules.run(checkIsCarExists(createCarDamageRequest.getCarId()));
        if (result != null){
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(Messages.DAMAGEADD);
    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        var result = BusinessRules.run(checkIsDamageIdExists(updateCarDamageRequest.getId()),checkIsCarExists(updateCarDamageRequest.getCarId()));
        if (result != null){
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(Messages.DAMAGEUPDATE);
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        var result = BusinessRules.run(checkIsDamageIdExists(deleteCarDamageRequest.getId()));
        if (result!=null){
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(deleteCarDamageRequest, CarDamage.class);
        this.carDamageDao.delete(carDamage);
        return new SuccessResult(Messages.DAMAGEDELETE);
    }

    @Override
    public DataResult<CarDamageSearchListDto> getById(int id) {

        var resultBusiness = BusinessRules.run(checkIsDamageIdExists(id));
        if (resultBusiness != null){
            return new ErrorDataResult(Messages.DAMAGENOTFOUND,null);
        }
        CarDamage result = this.carDamageDao.getById(id);
        CarDamageSearchListDto response = modelMapperService.forDto().map(result,CarDamageSearchListDto.class);
        return new SuccessDataResult<CarDamageSearchListDto>(response, Messages.DAMAGELIST);
    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getByCarId(int carId) {
        var resultBusiness = BusinessRules.run(checkIsCarExists(carId));
        if (resultBusiness != null){
            return new ErrorDataResult(Messages.CARNOTFOUND, null);
        }
        List<CarDamage> carDamageList = this.carDamageDao.getByCar_Id(carId);
        List<CarDamageSearchListDto> response = carDamageList.stream().map(carDamage -> modelMapperService.forDto().map(carDamage,CarDamageSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageSearchListDto>>(response, Messages.DAMAGELIST);
    }

    private Result checkIsCarExists(int carId){
        var existsResult =  this.carService.isCarExists(carId);
        if (!existsResult.isSuccess()){
            return new ErrorResult(Messages.CARNOTFOUND);
        }
        return new SuccessResult();
    }

    private Result checkIsDamageIdExists(int id){
        var existsResult = this.carDamageDao.existsById(id);
        if (!existsResult){
            return new ErrorResult(Messages.DAMAGENOTFOUND);
        }
        return new SuccessResult();
    }




}
