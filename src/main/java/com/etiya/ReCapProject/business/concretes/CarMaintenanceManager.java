package com.etiya.ReCapProject.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarMaintenanceService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.CreateCarMaintenanceRequest;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.DeleteCarMaintenanceRequest;
import com.etiya.ReCapProject.business.requests.carMaintenanceRequess.UpdateCarMaintenanceRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarMaintenanceDao;

import com.etiya.ReCapProject.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private RentalService rentalService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			CarService carService,@Lazy RentalService rentalService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<CarMaintenanceSearchListDto>> getAll() {
		List<CarMaintenance> result = this.carMaintenanceDao.findAll();
		List<CarMaintenanceSearchListDto> response = result.stream().map(
				carMaintenance -> modelMapperService.forDto().map(carMaintenance, CarMaintenanceSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceSearchListDto>>(response,Messages.CARMAINTENANCELIST);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		Result result = BusinessRules.run(isCarIdExists(createCarMaintenanceRequest.getCar_Id()),
				checkIfCarIsInMaintenance(createCarMaintenanceRequest.getCar_Id()),
				/*checkIfMaintenanceReturnDateBeforeToday(createCarMaintenanceRequest.getCar_Id()),*/
				checkRentalReturnDate(createCarMaintenanceRequest.getCar_Id()));
		if (result != null) {
			return result;
		}
		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.CARMAINTENANCEADD);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		var result = BusinessRules.run(checkIfMaintenanceIdExists(updateCarMaintenanceRequest.getId()),
				isCarIdExists(updateCarMaintenanceRequest.getCarId()),
				checkIfCarIsRented(updateCarMaintenanceRequest.getCarId()));
		if (result != null) {
			return result;
		}
		CarMaintenance carMaintenance = modelMapperService.forRequest().map(updateCarMaintenanceRequest,
				CarMaintenance.class);
		carMaintenance.setReturnDate(LocalDate.now());
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.CARMAINTENANCEUPDATE);
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		var result = BusinessRules.run(checkIfMaintenanceIdExists(deleteCarMaintenanceRequest.getId()));
		if (result != null) {
			return result;
		}
		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(deleteCarMaintenanceRequest.getId());
		this.carMaintenanceDao.delete(carMaintenance);
		return new SuccessResult(Messages.CARMAINTENANCEDELETE);
	}

	private Result checkIfCarIsRented(int carId) {
		var result = this.rentalService.isRentalExistsByCarId(carId);
		if (!result.isSuccess()){
			return new ErrorResult(Messages.CARMAINTENANCERENTALERROR);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<CarMaintenance> getByCar(int carId) {
		var carMaintenance = this.carMaintenanceDao.getByCar_Id(carId);
		if (carMaintenance == null) {
			return new ErrorDataResult(Messages.CARNOTFOUND);
		}
		return new SuccessDataResult<CarMaintenance>(carMaintenance);
	}

	@Override
	public boolean isCarExistsOnCarMaintenance(int carId) {
		var existsResult = this.carMaintenanceDao.existsByCarId(carId);
		if (!existsResult)
			{
				return false;
			}
			return true;

		}

	private Result isCarIdExists(int carId){

		var existsResult = this.carService.isCarExists(carId);
		if (!existsResult.isSuccess()){
			return new ErrorResult(Messages.CARNOTFOUND);
		}
		return new SuccessResult();
	}


	private Result checkIfCarIsInMaintenance(int carId) {
		var carIsInMaintenance = this.carMaintenanceDao.existsByCarId(carId);
		if (!carIsInMaintenance){
			return new SuccessResult();
		}
		var result = this.carMaintenanceDao.getByCar_Id(carId);
		if (result.getReturnDate()==null) {
			return new ErrorResult(Messages.CARMAINTENANCEALREADYEXISTS);
		}
		return new SuccessResult();
	}

	private Result checkIfMaintenanceIdExists(int id){
		var result = this.carMaintenanceDao.existsById(id);
		if (!result){
			return new ErrorResult(Messages.CARMAINTENANCENOTFOUND);
		}
		return new SuccessResult();
	}
	//araba kiradan dönmüş mü ? 
	private Result checkRentalReturnDate(int carId){
		var carExists = rentalService.getByCar_Id(carId).getData();
		if (carExists == null){
			return new SuccessResult();
		}
		var result=this.rentalService.getByCar_Id(carId).getData();
		if (result.getReturnDate()==null){
			return new ErrorResult(Messages.CARMAINTENANCERENTALERROR);
		}
		return new SuccessResult();
	}

}


