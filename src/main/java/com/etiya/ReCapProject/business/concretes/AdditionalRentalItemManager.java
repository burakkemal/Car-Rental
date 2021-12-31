package com.etiya.ReCapProject.business.concretes;

import com.etiya.ReCapProject.business.abstracts.AdditionalRentalItemService;
import com.etiya.ReCapProject.business.abstracts.AdditionalServiceService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.AdditionalRentalItemSearchListDto;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.CreateAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.DeleteAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.UpdateAdditionalRentalItemRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.*;
import com.etiya.ReCapProject.dataAccess.abstracts.AdditionalRentalItemDao;
import com.etiya.ReCapProject.entities.concretes.AdditionalRentalItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalRentalItemManager implements AdditionalRentalItemService {


    private ModelMapperService modelMapperService;
    private AdditionalRentalItemDao additionalRentalItemDao;
    private RentalService rentalService;
    private AdditionalServiceService additionalServiceService;

    @Autowired
    public AdditionalRentalItemManager(ModelMapperService modelMapperService, AdditionalRentalItemDao additionalRentalItemDao, RentalService rentalService, AdditionalServiceService additionalServiceService) {
        this.modelMapperService = modelMapperService;
        this.additionalRentalItemDao = additionalRentalItemDao;
        this.rentalService = rentalService;
        this.additionalServiceService = additionalServiceService;
    }

    @Override
    public DataResult<List<AdditionalRentalItemSearchListDto>> getAll() {
        List<AdditionalRentalItem> additionalRentalItems=this.additionalRentalItemDao.findAll();
        List<AdditionalRentalItemSearchListDto> additionalRentalItemSearchListDtos=additionalRentalItems.stream()
                .map(additional-> modelMapperService.forDto().map(additional,AdditionalRentalItemSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(additionalRentalItemSearchListDtos,Messages.ADDITIONALSERVICELIST);

    }
    @Override
    public Result add(CreateAdditionalRentalItemRequest createAdditionalRentalItemRequest) {

        var result = BusinessRules.run(isAdditionalServiceExists(createAdditionalRentalItemRequest.getAdditionalServiceId()),isRentalExists(createAdditionalRentalItemRequest.getRentalId()));
        if (result!=null){
            return result;
        }
        AdditionalRentalItem additionalRentalItem=modelMapperService.forRequest().map(createAdditionalRentalItemRequest,AdditionalRentalItem.class);
        this.additionalRentalItemDao.save(additionalRentalItem);
        return new SuccessResult(Messages.ADDITIONALRENTALITEMADD);
    }

    @Override
    public Result delete(DeleteAdditionalRentalItemRequest deleteAdditionalRentalItemRequest) {
        var result = BusinessRules.run(isAdditionalRentalItemExists(deleteAdditionalRentalItemRequest.getId()));
        if (result!=null){
            return result;
        }
        AdditionalRentalItem additionalRentalItem = modelMapperService.forRequest()
                .map(deleteAdditionalRentalItemRequest, AdditionalRentalItem.class);
        this.additionalRentalItemDao.delete(additionalRentalItem);
        return new SuccessResult(Messages.ADDITIONALRENTALITEMDELETE);
    }

    @Override
    public Result update(UpdateAdditionalRentalItemRequest updateAdditionalRentalItemRequest) {
        var result = BusinessRules.run(isAdditionalRentalItemExists(updateAdditionalRentalItemRequest.getId()),isAdditionalServiceExists(updateAdditionalRentalItemRequest.getAdditionalServiceId()),isRentalExists(updateAdditionalRentalItemRequest.getRentalId()));
        if (result!=null){
            return result;
        }
        AdditionalRentalItem additionalRentalItem = modelMapperService.forRequest()
                .map(updateAdditionalRentalItemRequest, AdditionalRentalItem.class);
        this.additionalRentalItemDao.save(additionalRentalItem);
        return new SuccessResult(Messages.ADDITIONALRENTALITEMUPDATE);
    }

    @Override
    public DataResult<List<AdditionalRentalItemSearchListDto>> getByRentalId(int rentalId) {
        var businessResult = BusinessRules.run(isRentalExists(rentalId));
        if (businessResult!=null){
            return new ErrorDataResult(Messages.ADDITIONALRENTALITEMNOTFOUND,null);
        }
        List<AdditionalRentalItem> request = this.additionalRentalItemDao.getByRentalId(rentalId);
        List<AdditionalRentalItemSearchListDto> response = request.stream()
                .map(additionalRentalItem -> modelMapperService.forDto()
                        .map(additionalRentalItem , AdditionalRentalItemSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalRentalItemSearchListDto>>(response, Messages.ADDITIONALRENTALITEMLIST);
    }



    private Result isRentalExists(int rentalId){
        var result = this.rentalService.isRentalExistsById(rentalId);
        if (!result.isSuccess()){
            return new ErrorResult(Messages.RENTALNOTFOUND);
        }
        return new SuccessResult();
    }

    private Result isAdditionalServiceExists(int id){
        var result = this.additionalServiceService.checkIsAdditionalServiceExists(id);
        if (!result.isSuccess()){
            return new ErrorResult(Messages.ADDITIONALSERVICENOTFOUND);
        }
        return new SuccessResult();
    }

    private Result isAdditionalRentalItemExists(int id){

        var result = this.additionalRentalItemDao.existsById(id);
        if (!result){
            return new ErrorResult(Messages.ADDITIONALRENTALITEMNOTFOUND);
        }
        return new SuccessResult();
    }






}
