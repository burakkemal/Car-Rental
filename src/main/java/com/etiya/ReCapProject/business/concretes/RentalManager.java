package com.etiya.ReCapProject.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


import com.etiya.ReCapProject.business.abstracts.*;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.ReCapProject.core.utilities.services.fakePos.externalFakePos.FakePosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.RentalSearchListDto;
import com.etiya.ReCapProject.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.ReCapProject.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.ReCapProject.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.services.findex.FindexService;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private FindexService findexService;
    private CarService carService;
    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;
    @org.springframework.context.annotation.Lazy
    private CarMaintenanceService carMaintenanceService;
    private UserService userService;
    private FakePosService fakePosService;
    private CityService cityService;

    @Autowired
    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, FindexService findexService,
                         CarService carService, IndividualCustomerService individualCustomerService,
                         @Lazy CarMaintenanceService carMaintenanceService, UserService userService, FakePosService fakePosService,CityService cityService,CorporateCustomerService corporateCustomerService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.findexService = findexService;
        this.carService = carService;
        this.individualCustomerService = individualCustomerService;
        this.carMaintenanceService = carMaintenanceService;
        this.userService = userService;
        this.fakePosService = fakePosService;
        this.cityService = cityService;
        this.corporateCustomerService = corporateCustomerService;
    }
    @Override
    public DataResult<List<RentalSearchListDto>> getAll() {
        List<Rental> result = this.rentalDao.findAll();
        List<RentalSearchListDto> response = result.stream()
                .map(rental -> modelMapperService.forDto().map(rental, RentalSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<RentalSearchListDto>>(response, Messages.RENTALLIST);
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {
        Result result = BusinessRules.run(
                checkIfUserIdExists(createRentalRequest.getCustomerId()),
                checkIfCarIdExists(createRentalRequest.getCarId()),
                compareFindexScores(createRentalRequest.getCarId()),
                checkIfCarIsInMaintenance(createRentalRequest.getCarId()),
                checkIfCarIsReturned(createRentalRequest.getCarId()),
                checkIfReturnDateIsNull(createRentalRequest.getCarId())
        );
        if (result != null) {
            return result;
        }
        var car=this.carService.getById(createRentalRequest.getCarId()).getData();
        createRentalRequest.setRentedKilometer(car.getKilometer());
        createRentalRequest.setRentedCityId(car.getCityId());
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setRentDate(LocalDate.now());
        CreatePaymentRequest createPaymentRequest=new CreatePaymentRequest();
        this.rentalDao.save(rental);
        return new SuccessResult(Messages.RENTALADD);
    }
    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        var result = BusinessRules.run(checkIfRentalExists(deleteRentalRequest.getId()));
        if (result != null) {
            return result;
        }
        Rental rental = modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
        return new SuccessResult(Messages.RENTALDELETE);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        var result = BusinessRules
                .run(isRentalExistsById(updateRentalRequest.getId()),
                checkIfUserIdExists(updateRentalRequest.getCustomerId()),
                checkIfCarIdExists(updateRentalRequest.getCarId()),
                        checkIfCarIsInMaintenance(updateRentalRequest.getCarId()),
                checkIfLimitIsEnough(updateRentalRequest.getId(),updateRentalRequest.getCreatePaymentRequest()),
                checkIfCityExists(updateRentalRequest.getReturnCityId()));
        if (result != null) {
            return result;
        }
        var tempRental = this.rentalDao.getById(updateRentalRequest.getId());
        var car=this.carService.getById(updateRentalRequest.getCarId()).getData();
        updateRentalRequest.setRentDate(tempRental.getRentDate());
        updateRentalRequest.setRentedCityId(car.getCityId());
        updateRentalRequest.setRentedKilometer(car.getKilometer());
        Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.carService.updateCity(rental.getReturnCityId(),rental.getCar().getId());
        this.carService.updateKilometer(updateRentalRequest.getReturnedKilometer(),rental.getCar().getId());
        rentalDao.save(rental);
        return new SuccessResult(Messages.RENTALUPDATE);
    }


    public Result checkIfReturnDateIsNull(int carId) {
        var result = this.rentalDao.existsByCarId(carId);
        if(!result){
            return new SuccessResult();
        }
        Rental rental = this.rentalDao.getByCarId(carId);
        if (rental.getReturnDate() == null) {
            return new ErrorResult(Messages.RENTALDATEISNULL);
        }
        return new SuccessResult();
    }

    @Override
    public int getAdditionalItemsTotalPriceByRentalId(int rentalId) {
        var result = this.rentalDao.getAdditionalItemsOfRelevantRental(rentalId);
        int totalAmount=0;
        for(int item : result){
            totalAmount+=item;
        }
        return totalAmount;
    }

    @Override
    public Result isRentalExistsById(int id) {
        var result = this.rentalDao.existsById(id);
        if (!result){
            return new ErrorResult(Messages.RENTALNOTFOUND);
        }
        return new SuccessResult();
    }

    @Override
    public Result isRentalExistsByCarId(int carId) {
        var result = this.rentalDao.existsByCarId(carId);
        if(result){
            return new ErrorResult(Messages.RENTALFOUND);
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<RentalSearchListDto> getByRentalId(int id) {
        var result = this.rentalDao.existsById(id);
        if (!result){
            return new ErrorDataResult<>(Messages.RENTALNOTFOUND, null);
        }
        var rentalResult = this.rentalDao.getById(id);

        RentalSearchListDto rentalSearchListDto = modelMapperService.forDto().map(rentalResult,RentalSearchListDto.class);

return new SuccessDataResult<RentalSearchListDto>(rentalSearchListDto, Messages.RENTALFOUND);

    }

    private Result compareFindexScores(int id) {
        var resultCar = carService.isCarExists(id);
        if (!resultCar.isSuccess()) {
            return new ErrorResult(Messages.CARNOTFOUND);
        }
        var car = carService.getById(id);
        if (car == null) {
            return new ErrorResult(Messages.CARNOTFOUND);
        }
        int findexScore = car.getData().getFindexScore();
        int customerFindexScore = findexService.calculateCustomerFindexScore();
        if (customerFindexScore < findexScore) {
            return new ErrorResult(Messages.RENTALFINDEXSCOREERROR);
        }
        return new SuccessResult(" " + customerFindexScore);
    }

    @Override
    public DataResult<Rental> getByCar_Id(int carId) {
        var rental = this.rentalDao.getByCarId(carId);
        if (rental == null) {
            return new ErrorDataResult(Messages.CARNOTFOUND,null);
        }
        return new SuccessDataResult<Rental>(rental);
    }

    private Result checkIfCarIsInMaintenance(int carId) {
        var existsResult = this.carMaintenanceService.isCarExistsOnCarMaintenance(carId);
        if (!existsResult){
            return new SuccessResult();
        }
        var result = this.carMaintenanceService.getByCar(carId);
        if (result.getData().getReturnDate() == null) {
            return new ErrorResult(Messages.RENTALMAINTENANCEERROR);
        }
        return new SuccessResult();
    }

    private Result checkIfCarIdExists(int carId) {
        var result = this.carService.isCarExists(carId);
        if (!result.isSuccess()) {
            return new ErrorResult(Messages.CARNOTFOUND);
        }
        return new SuccessResult();
    }

    private Result checkIfUserIdExists(int carId) {
        var result = this.userService.isUserExists(carId);
        if (!result.isSuccess()) {
            return new ErrorResult(Messages.USERNOTFOUND);
        }
        return new SuccessResult();
    }

    private Result checkIfRentalExists(int id) {
        boolean result = this.rentalDao.existsById(id);
        if (!result) {
            return new ErrorResult(Messages.RENTALNOTFOUND);
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<Rental> getById(int id) {
        var result = this.rentalDao.getById(id);
        if (result == null) {
            return new ErrorDataResult<Rental>(Messages.RENTALNOTFOUND, null);
        }
        return new SuccessDataResult<Rental>(result);
    }


    private Result checkIsRentDateIsAfterThanReturnDate(LocalDate rentDate,LocalDate returnDate){
        rentDate=LocalDate.now();
        if ((rentDate.isAfter(returnDate))){
            return new ErrorResult(Messages.RENTALDATEERROR);
        }
        return new SuccessResult();
    }

    private Result checkIfCarIsReturned(int carId){
        boolean carResult = this.rentalDao.existsByCarId(carId);
        if (!carResult)
        {
            return new SuccessResult();
        }else{
            Rental result = this.rentalDao.getByCarId(carId);
            if (result.getReturnDate() == null){
                return new ErrorResult(Messages.RENTALDATEISNULL);
            }
            return new SuccessResult();
        }
    }

    private Result checkIfCityExists(int cityId){
        var existsResult = this.cityService.isCityIdExist(cityId);
        if (!existsResult.isSuccess()){
            return new ErrorResult(Messages.CITYNOTFOUND);
        }
        return new SuccessResult();
    }
    private Result checkIfLimitIsEnough(int rentalId, CreatePaymentRequest createPaymentRequest) {
        Rental rental = this.rentalDao.getById(rentalId);
        var car = this.carService.getById(rental.getCar().getId());
        int totalDay = (int) (ChronoUnit.DAYS.between(rental.getRentDate(), LocalDate.now()));
        int additionalTotalAmount = getAdditionalItemsTotalPriceByRentalId(rental.getId());
        var totalAmount = (car.getData().getDailyPrice()+additionalTotalAmount)* totalDay;
        var comparisonResult = compareCityId(car.getData().getCityId(), rental.getReturnCityId());
        if (!comparisonResult.isSuccess()) {
            totalAmount += 500;
        }
        createPaymentRequest.setPrice(totalAmount);
        if (!this.fakePosService.payByCreditCard(createPaymentRequest)) {
            return new ErrorResult(Messages.INSUFFICIENTBALANCE+" "+totalAmount+" " + additionalTotalAmount+" "+ totalDay);
        }
        return new SuccessResult(Messages.SUFFICIENTBALANCE);
    }
    public Result compareCityId(int rentalCityId, int availableCityId) {
        if (rentalCityId != availableCityId) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }



    //
}
